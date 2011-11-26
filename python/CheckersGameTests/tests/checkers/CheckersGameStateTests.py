from checkers.CheckersPlayer import CheckersPlayer
from checkers.CheckersGameState import CheckersGameState
import unittest

class CheckersGameStateTests(unittest.TestCase):
    
    def test_initialStateGetCurrentPlayer_returnsBlackPlayer(self):
        # Arrange
        state = CheckersGameState()
        # Act
        player = state.getCurrentPlayer()
        # Assert
        self.assertEqual(CheckersPlayer.BLACK, player.getColor(), "The black player plays first")
    
    def test_initialStateGetActions_returns7Actions(self):
        # Arrange
        state = CheckersGameState()
        # Act
        actions = state.getActions()
        # Assert
        self.assertEqual(7, len(actions), "The initial state should have exactly 7 states")
    
    def test_initialStateGetActions_returnsGameIsNotOver(self):
        # Arrange
        state = CheckersGameState()
        # Act
        isGameOver = state.isGameOver()
        # Assert
        self.assertFalse(isGameOver, "At its initial state, the game should not be over")