import webapp2
import json
import string
from google.appengine.ext import db

import logging

logging.getLogger().setLevel(logging.DEBUG)

class Word(db.Model):
  word = db.StringProperty(required=True)
  exists = db.BooleanProperty()

class Template:
  def __init__(self, template, bad_letters):
    self.template = template
    self.bad_letters = bad_letters

  def words(self):
    used = set(self.template) - set('?')
    left = ''.join(set(string.ascii_lowercase) - self.bad_letters - used)
    exploded = []
    for l in self.template:
      if l == '?':
        exploded.append(left)
      else:
        exploded.append(l)

    logging.debug('%s'%exploded)

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
    
    for l in template:
      if l not in (string.ascii_lowercase + '?'):
        self.response.out.write(json.dumps({'error':'illegal template'}))
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
      results.append(nextWord)
    
    return results
