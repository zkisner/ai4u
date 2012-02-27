import webapp2
import json
from google.appengine.ext import db

class Word(db.Model):
  word = db.StringProperty(required=True)
  exists = db.BooleanProperty()

class WordsRequestHandler(webapp2.RequestHandler):
  def get(self):
    self.response.out.write(json.dumps({'value':1}))

