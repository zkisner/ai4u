import webapp2
from words import WordsRequestHandler

app = webapp2.WSGIApplication([
    ('/words', WordsRequestHandler),
  ], debug=True)
