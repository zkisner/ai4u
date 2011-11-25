'''
Created on Sep 11, 2011

@author: kreich
'''

class GameState(object):
    '''
    Represents a general game state.
    '''

    def getCurrentPlayer(self):
        '''
        Returns the player whose turn is next.
        '''
        return self.currPlayer
    
    def getActions(self):
        '''
        Returns the available actions from the current state.
        '''
        raise NotImplementedError()
    
    def isGameOver(self):
        '''
        Returns whether the game is over.
        '''
        raise NotImplementedError()