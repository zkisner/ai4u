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
    self.template = template
    self.bad_letters = bad_letters

  def words(self):
    used = set(self.template) - set('?')
    left = ''.join(set(string.ascii_lowercase) - self.bad_letters - used)
    left_no_vowels = ''.join(set(left) - set('aeiou'))
    last_vowel = max([self.template.rfind(l) for l in 'aeiou'])
    exploded = []
    for i,l in enumerate(self.template):
      if l == '?':
        if last_vowel >= 0 and i > last_vowel:
          exploded.append(left_no_vowels)
        else:
          exploded.append(left)
      else:
        exploded.append(l)

    indices = [0] * len(exploded)
    while indices[0] < len(exploded[0]):
      yield self.makeWord(exploded, indices)
      indices = self.nextIndices(exploded, indices)

  def makeWord(self, exploded, indices):
    word = ''
    for i in range(len(exploded)):
      word += exploded[i][indices[i]]

    return word

  def nextIndices(self, exploded, indices):
    new_indices = [i for i in indices]
    new_indices[-1] += 1

    index = len(new_indices) - 1
    while index > 0 and new_indices[index] == len(exploded[index]):
      new_indices[index] = 0
      new_indices[index-1] += 1
      index -= 1

    return new_indices


class WordsRequestHandler(webapp2.RequestHandler):
  def get(self):
    template = self.request.get('arg0')[1:-1]
    letters = self.request.get('arg1')[1:-1]
    
    logging.debug("Handling new request: " + template + "," + letters)

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

    results = self.doSearch(template, letters)
    self.response.out.write(json.dumps({'results':results}))

  def doSearch(self, template, letters):
    results = []
    for nextWord in template.words():
      query = Word.all().filter("word =", nextWord)
      word = query.get()

      if not word:
        word = Word()
        word.word = nextWord
        word.exists = self.wordInMorfix(nextWord)
        word.put()

      logging.debug("Searching word: %s [%s]" % (word.word, word.exists))
      if word.exists:
        results.append(word.word)
        logging.debug("Found word: " + word.word)
    
    return results

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

