'''
Created on Sep 11, 2011

@author: kreich
'''
from interfaces.GameState import GameState
from checkers.CheckersPlayer import BlackCheckersPlayer

class CheckersGameState(GameState):
    '''
    Represents a state in a checkers game.
    '''

    def __init__(self):
        '''
        Constructor.
        Sets the Black player as the starting player.
        '''
        self.currPlayer = BlackCheckersPlayer()
    
    def getActions(self):
        '''
        Returns the available actions from the current state.
        '''
        return range(7)
    
    def isGameOver(self):
        '''
        Returns whether the game is over.
        '''
        return False