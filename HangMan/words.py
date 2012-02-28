import webapp2
import json
import string
import httplib
from google.appengine.ext import db

import logging

logging.getLogger().setLevel(logging.DEBUG)

class Word(db.Model):
  word = db.StringProperty()
  exists = db.BooleanProperty()

class Template:
  def __init__(self, template, bad_letters):
    self.orig = template
    used = set(template) - set('?')
    left = ''.join(set(string.ascii_lowercase) - bad_letters - used)
    left_no_vowels = ''.join(set(left) - set('aeiou'))
    last_vowel = max([template.rfind(l) for l in 'aeiou'])
    self.exploded = []
    for i,l in enumerate(template):
      if l == '?':
        if last_vowel >= 0 and i > last_vowel:
          self.exploded.append(left_no_vowels)
        else:
          self.exploded.append(left)
      else:
        self.exploded.append(l)

    self.length = 1
    for l in self.exploded:
      self.length *= len(l)
    self.indices = [0]*len(self.exploded)

  def words(self):
    while self.indices[0] < len(self.exploded[0]):
      yield self.nextWord()
      self.nextIndices()

  def nextWord(self):
    word = ''
    for i in range(len(self.exploded)):
      word += self.exploded[i][self.indices[i]]

    return word

  def nextIndices(self):
    self.indices[-1] += 1

    index = len(self.indices) - 1
    while index > 0 and self.indices[index] == len(self.exploded[index]):
      self.indices[index] = 0
      self.indices[index-1] += 1
      index -= 1

  def skip(self, num):
    i = 0
    while i < num:
      self.nextIndices()
      i += 1


class WordsRequestHandler(webapp2.RequestHandler):
  def get(self):
    template = self.request.get('arg0')[1:-1]
    letters = self.request.get('arg1')[1:-1]
    if self.request.get('arg2'):
      progress = int(self.request.get('arg2'))
    else:
      progress = None
    
    logging.info("Handling new request: %s,%s,%s" % (template, letters, progress))

    for l in template:
      if l not in (string.ascii_lowercase + '?'):
        self.response.out.write(json.dumps({'error':'illegal template'}))
        return
    if len(template) < 4:
      self.response.out.write(json.dumps({'error':'template is too short'}))
      return
    if len(template) > 8:
      self.response.out.write(json.dumps({'error':'template is too long'}))
      return

    for l in letters:
      if l not in string.ascii_lowercase:
        self.response.out.write(json.dumps({'error':'illegal letters'}))
        return
    letters = set(letters)
    template = Template(template,letters)

    resultObject = {'done': False, 'results':[], 'progress':[0,template.length], 'template':template.orig, 'letters':''.join(letters)}
    if progress is not None:
      template.skip(progress)
      resultObject['progress'][0] = progress
      self.search(template, resultObject)
    
    self.response.out.write(json.dumps(resultObject))

  def search(self, template, resultObject):
    try:
      nextWord = template.words().next()

      logging.info("Searching word: %s" % nextWord)
      
      query = Word.all().filter("word =", nextWord)
      word = query.get()

      if not word:
        word = Word()
        word.word = nextWord
        word.exists = self.wordInMorfix(nextWord)
        word.put()

      resultObject['progress'][0] += 1

      logging.info("Searching word: %s [%s]" % (word.word, word.exists))
      if word.exists:
        resultObject['results'].append(word.word)
        logging.info("Found word: " + word.word)

    except StopIteration:
      resultObject['done'] = True
      resultObject['progress'][0] = template.length

  def wordInMorfix(self, word):
    while True:
      try:
        conn = httplib.HTTPConnection('morfix.nana10.co.il')
        conn.request('GET', '/default.aspx?q=' + word + '&source=milon')
        data = conn.getresponse().read()
        conn.close()

        return (data.find('<span class="word">') > 0)
      except:
        pass

