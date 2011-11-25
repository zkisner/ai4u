'''
Created on Sep 11, 2011

@author: kreich
'''
from interfaces.Player import Player

class CheckersPlayer(Player):
    '''
    Represents a player, playing the Checkers game.
    '''

    BLACK = "B"

    def __init__(self, color):
        '''
        Constructor
        '''
        self.color = color
    
    def getColor(self):
        '''
        Returns the color of the current player.
        '''
        return self.color

class BlackCheckersPlayer(CheckersPlayer):
    '''
    Represents the black checkers player.
    '''
    
    def __init__(self):
        '''
        Constructor
        '''
        CheckersPlayer.__init__(self, CheckersPlayer.BLACK)