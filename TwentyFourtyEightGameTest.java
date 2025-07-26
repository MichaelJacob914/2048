package org.cis1200.TwentyFourtyEight;

import org.cis1200.twentyfourtyeight.TwentyFourtyEight;
import org.cis1200.twentyfourtyeight.Tile;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.io.*;


/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class TwentyFourtyEightGameTest {

    TwentyFourtyEight tester = new TwentyFourtyEight(true);

    @Test
    public void testBlankBoard() {
        tester.reset();
        tester.reset();
        Tile[][] board = tester.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 1)) {

                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }

    }

    @Test
    public void testLeftMove() {
        tester.reset();
        tester.reset();
        tester.leftMove();
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 && j == 0) {

                    assertEquals(4, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }

    }

    @Test
    public void testLeftMoveAndGeneration() {
        tester.reset();
        tester.reset();
        tester.playTurn(1);
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 && j == 0) {
                    assertEquals(4, board[i][j].getValue());
                } else if (i == 0 && j == 1) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }

    }

    @Test
    public void testUpMove() {
        tester.reset();
        tester.reset();
        tester.upMove();
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 1)) {

                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
    }
    @Test
    public void testUpMoveAndGeneration() {
        tester.reset();
        tester.reset();
        tester.playTurn(3);
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 1)) {

                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
    }

    @Test
    public void testDownMove() {
        tester.reset();
        tester.reset();
        tester.downMove();
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 3 && j == 0) || (i == 3 && j == 1)) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
    }
    @Test
    public void testDownMoveAndGeneration() {
        tester.reset();
        tester.reset();
        tester.playTurn(4);
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 3 && j == 0) || (i == 3 && j == 1)) {
                    assertEquals(2, board[i][j].getValue());
                } else if (i == 0 && j == 0) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
    }

    @Test
    public void testRightMoveAndGeneration() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 && j == 3)) {
                    assertEquals(4, board[i][j].getValue());
                } else if (i == 0 && j == 0) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
    }
    @Test
    public void testSequenceOfMoves() {
        tester.reset();
        tester.reset();
        tester.playTurn(1);
        Tile[][] board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 && j == 0) {
                    assertEquals(4, board[i][j].getValue());
                } else if (i == 0 && j == 1) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
        tester.playTurn(2);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 && j == 3) || (i == 0 && j == 0)) {
                    assertEquals(2, board[i][j].getValue());
                } else if (i == 0 && j == 2)  {
                    assertEquals(4, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }

        tester.playTurn(4);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 3 && j == 3) || (i == 3 && j == 0)) {
                    assertEquals(2, board[i][j].getValue());
                } else if (i == 3 && j == 2)  {
                    assertEquals(4, board[i][j].getValue());
                } else if (i == 0 && j == 0) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }

        tester.playTurn(4);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 3 && j == 3)) {
                    assertEquals(2, board[i][j].getValue());
                } else if ((i == 3 && j == 2) || (i == 3 && j == 0))    {
                    assertEquals(4, board[i][j].getValue());
                } else if (i == 0 && j == 0) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
    }
    @Test
    public void testNoChangeEdgeCase() {
        tester.reset();
        tester.reset();
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        int value = 2;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
                value = 2 * value;
            }
        }
        value = 2;
        tester.playTurn(1);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                assertEquals(value, board[j][i].getValue());
                value = 2 * value;
            }
        }
        assertFalse(tester.hasLegalMove());
    }
    @Test
    public void testHasLegalMove() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        assertTrue(tester.hasLegalMove());
    }
    @Test
    public void testNoLegalMove() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        int value = 2;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
                value = 2 * value;
            }
        }
        assertFalse(tester.hasLegalMove());
    }
    @Test
    public void testReachedGoal() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        tester.setCell(0,0, 2048);
        assertTrue(tester.hasLegalMove());
        assertEquals(1, tester.checkWinner());
    }

    @Test
    public void testGameOver() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        int value = 2;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
                if (value != 1024) {
                    value = 2 * value;
                } else {
                    value = 4 * value;
                }
            }
        }
        assertFalse(tester.hasLegalMove());
        assertEquals(2, tester.checkWinner());
    }

    @Test
    public void testScore() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        int value = 2;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
            }
        }
        tester.calculateScore();
        assertEquals(32, tester.getScore());
        value = 4;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
            }
        }
        tester.calculateScore();
        assertEquals(64, tester.getScore());
    }

    @Test
    public void testBest() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        int value = 4;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
            }
        }
        tester.calculateScore();
        tester.writeOut("2048.txt");
        assertEquals(tester.getBest(), 64);
        value = 2;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
            }
        }
        tester.writeOut("2048.txt");
        tester.calculateScore();
        assertEquals(tester.getBest(), 64);
        tester.reset();
        tester.writeOut("2048.txt");
    }

    @Test
    public void testReset() {
        tester.reset();
        tester.reset();
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        int value = 4;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i, j, value);
            }
        }
        tester.reset();
        board = tester.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 1)) {
                    assertEquals(2, board[i][j].getValue());
                } else {
                    assertEquals(0, board[i][j].getValue());
                }
            }
        }
        assertEquals(0, tester.getScore());
    }

    @Test
    public void testWriteAndReadFileTest() {
        tester.reset();
        tester.reset();
        tester.playTurn(2);
        assertTrue(tester.hasLegalMove());
        Tile[][] board = tester.getBoard();
        int value = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tester.setCell(i,j, value);
            }
        }
        tester.calculateScore();
        tester.writeOut("2048.txt");
        tester.readIn("2048.txt");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                assertEquals(0, board[i][j].getValue());
            }
        }
    }



}

