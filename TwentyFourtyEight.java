package org.cis1200.twentyfourtyeight;

/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;

/**
 * This class is a model for TicTacToe.
 * 
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games. We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec36.pdf
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
public class TwentyFourtyEight {

    private Tile[][] board;
    private boolean gameOver;

    private int score;

    private boolean fixedGeneration;


    private int max;

    /**
     * Constructor sets up game state.
     */
    public TwentyFourtyEight(boolean fixed) {
        max = 0;
        fixedGeneration = fixed;
    }

    /**
     * playTurn allows players to play a turn. Returns true if the move is
     * successful and false if a player tries to play in a location that is
     * taken or after the game has ended. If the turn is successful and the game
     * has not ended, the player is changed. If the turn is unsuccessful or the
     * game has ended, the player is not changed.
     *
     * @param direction arrow key pressed
     * @param
     * @return whether the turn was successful
     */
    public boolean playTurn(int direction) {
        boolean turnHappened;
        if (direction == 1) {
            turnHappened = leftMove();
        } else if (direction == 2) {
            turnHappened = rightMove();
        } else if (direction == 3) {
            turnHappened = upMove();
        } else if (direction == 4) {
            turnHappened = downMove();
        } else {
            throw new IllegalArgumentException();
        }

        printGameState();
        if (turnHappened) {
            printGameState();
            if (fixedGeneration) {
                fixedGenerate();
            } else {
                generate();
            }
            calculateScore();
        }

        if (checkWinner() == 2 || checkWinner() == 1) {
            gameOver = true;
            return true;
        }
        return turnHappened;
    }

    public void generate() {
        int x;
        int y;
        boolean generated = false;
        while (!generated) {
            x = (int)(Math.random() * 4);
            y = (int)(Math.random() * 4);
            if (board[x][y].getValue() == 0) {
                int value = (int)(Math.random() * 10);
                if (value <= 8) {
                    value = 2;
                } else {
                    value = 4;
                }
                board[x][y] = new Tile(value, Integer.toString(value));
                generated = true;
            }

        }
    }

    public void fixedGenerate() {

        boolean generated = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getValue() == 0 && !generated) {
                    int value = 2;
                    board[i][j] = new Tile(value, Integer.toString(value));
                    generated = true;
                }
            }
        }

    }


    public boolean leftMove() {
        Tile[][] temp = new Tile[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp[i][j] = board[i][j];
            }
        }
        boolean proceed = true;
        for (int i = 0; i < board.length; i++) {
            int j = 1;
            proceed = true;

            while (proceed) {
                if (j == 4 || board[i][0].getValue() == 0) {
                    break;
                }

                if (board[i][0].equals(board[i][j])) {

                    board[i][0].doubleValue();
                    board[i][j] = new Tile(0, "0");

                } else if (board[i][j].getValue() != 0) {
                    proceed = false;
                }
                j++;

            }
            proceed = true;
            j = 2;
            while (proceed) {
                if (j == 4 || board[i][1].getValue() == 0) {
                    break;
                }

                if (board[i][1].equals(board[i][j])) {

                    board[i][1].doubleValue();
                    board[i][j] = new Tile(0, "0");

                } else if (board[i][j].getValue() != 0) {
                    proceed = false;
                }
                j++;
            }

            if (board[i][2].getValue() != 0) {
                if (board[i][2].getValue() == board[i][3].getValue()) {
                    board[i][2].doubleValue();
                    board[i][3] = new Tile(0, "0");
                }
            }

            if (!(board[i][0].getValue() == board[i][1].getValue() &&
                    board[i][0].getValue() == board[i][2].getValue() &&
                    board[i][0].getValue() == board[i][3].getValue() &&
                    board[i][0].getValue() == 0)) {
                while (board[i][0].getValue() == 0) {
                    board[i][0] = board[i][1];
                    board[i][1] = board[i][2];
                    board[i][2] = board[i][3];
                    board[i][3] = new Tile(0, "0");
                }
            }

            if (!(board[i][1].getValue() == board[i][2].getValue() &&
                    board[i][1].getValue() == board[i][3].getValue() &&
                    board[i][1].getValue() == 0)) {
                while (board[i][1].getValue() == 0) {
                    board[i][1] = board[i][2];
                    board[i][2] = board[i][3];
                    board[i][3] = new Tile(0, "0");
                }
            }

            if (!(board[i][2].getValue() == board[i][3].getValue() &&
                    board[i][2].getValue() == 0)) {
                if (board[i][2].getValue() == 0) {
                    board[i][2] = board[i][3];
                    board[i][3] = new Tile(0, "0");
                }
            }


        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (temp[i][j].getValue() != board[i][j].getValue()) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean rightMove() {
        Tile[][] temp = new Tile[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp[i][j] = board[i][j];
            }
        }
        boolean proceed = true;
        for (int i = 3; i >= 0; i--) {
            int j = 2;
            proceed = true;

            while (proceed) {
                if (j == -1 || board[i][3].getValue() == 0) {
                    break;
                }

                if (board[i][3].equals(board[i][j])) {

                    board[i][3].doubleValue();
                    board[i][j] = new Tile(0, "0");

                } else if (board[i][j].getValue() != 0) {
                    proceed = false;
                }
                j--;

            }
            proceed = true;
            j = 1;
            while (proceed) {
                if (j == -1 || board[i][2].getValue() == 0) {
                    break;
                }

                if (board[i][2].equals(board[i][j])) {

                    board[i][2].doubleValue();
                    board[i][j] = new Tile(0, "0");

                } else if (board[i][j].getValue() != 0) {
                    proceed = false;
                }
                j--;
            }

            if (board[i][1].getValue() != 0) {
                if (board[i][1].getValue() == board[i][0].getValue()) {
                    board[i][1].doubleValue();
                    board[i][0] = new Tile(0, "0");
                }
            }

            if (!(board[i][3].getValue() == board[i][2].getValue() &&
                    board[i][3].getValue() == board[i][1].getValue() &&
                    board[i][3].getValue() == board[i][0].getValue() &&
                    board[i][3].getValue() == 0)) {
                while (board[i][3].getValue() == 0) {
                    board[i][3] = board[i][2];
                    board[i][2] = board[i][1];
                    board[i][1] = board[i][0];
                    board[i][0] = new Tile(0, "0");
                }
            }

            if (!(board[i][2].getValue() == board[i][1].getValue() &&
                    board[i][2].getValue() == board[i][0].getValue() &&
                    board[i][2].getValue() == 0)) {
                while (board[i][2].getValue() == 0) {
                    board[i][2] = board[i][1];
                    board[i][1] = board[i][0];
                    board[i][0] = new Tile(0, "0");
                }
            }

            if (!(board[i][1].getValue() == board[i][0].getValue() &&
                    board[i][1].getValue() == 0)) {
                if (board[i][1].getValue() == 0) {
                    board[i][1] = board[i][0];
                    board[i][0] = new Tile(0, "0");
                }
            }


        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (temp[i][j].getValue() != board[i][j].getValue()) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean downMove() {
        Tile[][] temp = new Tile[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp[i][j] = board[i][j];
            }
        }
        boolean proceed = true;
        for (int i = 3; i >= 0; i--) {
            int j = 2;
            proceed = true;

            while (proceed) {
                if (j == -1 || board[3][i].getValue() == 0) {
                    break;
                }

                if (board[3][i].equals(board[j][i])) {

                    board[3][i].doubleValue();
                    board[j][i] = new Tile(0, "0");

                } else if (board[j][i].getValue() != 0) {
                    proceed = false;
                }
                j--;

            }
            proceed = true;
            j = 1;
            while (proceed) {
                if (j == -1 || board[2][i].getValue() == 0) {
                    break;
                }

                if (board[2][i].equals(board[j][i])) {

                    board[2][i].doubleValue();
                    board[j][i] = new Tile(0, "0");

                } else if (board[j][i].getValue() != 0) {
                    proceed = false;
                }
                j--;
            }

            if (board[1][i].getValue() != 0) {
                if (board[1][i].getValue() == board[0][i].getValue()) {
                    board[1][i].doubleValue();
                    board[0][i] = new Tile(0, "0");
                }
            }

            if (!(board[3][i].getValue() == board[2][i].getValue() &&
                    board[3][i].getValue() == board[1][i].getValue() &&
                    board[3][i].getValue() == board[0][i].getValue() &&
                    board[3][i].getValue() == 0)) {
                while (board[3][i].getValue() == 0) {
                    board[3][i] = board[2][i];
                    board[2][i] = board[1][i];
                    board[1][i] = board[0][i];
                    board[0][i] = new Tile(0, "0");
                }
            }

            if (!(board[2][i].getValue() == board[1][i].getValue() &&
                    board[2][i].getValue() == board[0][i].getValue() &&
                    board[2][i].getValue() == 0)) {
                while (board[2][i].getValue() == 0) {
                    board[2][i] = board[1][i];
                    board[1][i] = board[0][i];
                    board[0][i] = new Tile(0, "0");
                }
            }

            if (!(board[1][i].getValue() == board[0][i].getValue() &&
                    board[1][i].getValue() == 0)) {
                if (board[1][i].getValue() == 0) {
                    board[1][i] = board[i][0];
                    board[0][i] = new Tile(0, "0");
                }
            }


        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (temp[i][j].getValue() != board[i][j].getValue()) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean upMove() {
        Tile[][] temp = new Tile[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp[i][j] = board[i][j];
            }
        }
        boolean proceed = true;
        for (int i = 0; i < board[0].length; i++) {
            int j = 1;
            proceed = true;
            while (proceed) {
                if (j == 4 || board[0][i].getValue() == 0) {
                    break;
                }

                if (board[0][i].equals(board[j][i])) {
                    board[0][i].doubleValue();
                    board[j][i] = new Tile(0, "0");

                } else if (board[j][i].getValue() != 0) {
                    proceed = false;
                }
                j++;

            }
            proceed = true;
            j = 2;
            while (proceed) {
                if (j == 4 || board[1][i].getValue() == 0) {
                    break;
                }

                if (board[1][i].equals(board[j][i])) {

                    board[1][i].doubleValue();
                    board[j][i] = new Tile(0, "0");

                } else if (board[j][i].getValue() != 0) {
                    proceed = false;
                }
                j++;
            }

            if (board[2][i].getValue() != 0) {
                if (board[2][i].getValue() == board[3][i].getValue()) {
                    board[2][i].doubleValue();
                    board[3][i] = new Tile(0, "0");
                }
            }

            if (!(board[0][i].getValue() == board[1][i].getValue() &&
                    board[0][i].getValue() == board[2][i].getValue() &&
                    board[0][i].getValue() == board[3][i].getValue() &&
                    board[0][i].getValue() == 0)) {
                while (board[0][i].getValue() == 0) {
                    board[0][i] = board[1][i];
                    board[1][i] = board[2][i];
                    board[2][i] = board[3][i];
                    board[3][i] = new Tile(0, "0");
                }
            }

            if (!(board[1][i].getValue() == board[2][i].getValue() &&
                    board[1][i].getValue() == board[3][i].getValue() &&
                    board[1][i].getValue() == 0)) {
                while (board[1][i].getValue() == 0) {
                    board[1][i] = board[2][i];
                    board[2][i] = board[3][i];
                    board[3][i] = new Tile(0, "0");
                }
            }

            if (!(board[2][i].getValue() == board[3][i].getValue() &&
                    board[2][i].getValue() == 0)) {
                if (board[2][i].getValue() == 0) {
                    board[2][i] = board[3][i];
                    board[3][i] = new Tile(0, "0");
                }
            }


        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (temp[i][j].getValue() != board[i][j].getValue()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * checkWinner checks whether the game has reached a win condition.
     * checkWinner only looks for horizontal wins.
     *
     * @return 0 if nobody has won yet, 1 if player 1 has won, and 2 if player 2
     *         has won, 3 if the game hits stalemate
     */
    public int checkWinner() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getValue() == 2048) {
                    return 1;
                }
            }
        }
        if (!hasLegalMove()) {
            return 2;
        }

        return 0;
    }

    public boolean hasLegalMove() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getValue() == 0) {
                    return true;
                } else if (i - 1 >= 0) {
                    if (board[i - 1][j].equals(board[i][j])) {
                        return true;
                    }
                } else if (j - 1 >= 0) {
                    if (board[i][j - 1].equals(board[i][j])) {
                        return true;
                    }
                } else if (i <= board.length - 2) {
                    if (board[i + 1][j].equals(board[i][j])) {
                        return true;
                    }
                } else if (j <= board.length - 2) {
                    if (board[i][j + 1].equals(board[i][j])) {
                        return true;
                    }
                }

            }
        }
        return false;


    }

    public void calculateScore() {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sum += board[i][j].getValue();
            }
        }
        score = sum;
        if (score >= max) {
            max = score;
        }
    }

    public int getBest() {
        return max;
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("___________________");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("| " + board[i][j].getValue() + " |");
            }
            System.out.println();
        }
        System.out.println("__________________");
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new Tile[4][4];
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Tile(0, "0");
            }
        }
        if (fixedGeneration) {
            fixedGenerate();
            fixedGenerate();
        } else {
            generate();
            generate();
        }

        gameOver = false;
        score = 0;

    }

    public int getScore() {
        return score;
    }



    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board. 0 = empty, 1 = Player 1, 2 = Player 2
     */
    public Tile getCell(int c, int r) {
        return board[r][c];
    }
    public void setCell(int c, int r, int value) {
        board[r][c] = new Tile(value, Integer.toString(value));
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void readIn(String fileName) {
        BufferedReader br = FileLineIterator.fileToReader(fileName);
        FileLineIterator fl = new FileLineIterator(br);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (fl.hasNext()) {
                    String temp = fl.next();
                    board[i][j] = new Tile(Integer.parseInt(temp), temp);
                }
            }
        }
        if (fl.hasNext()) {
            String temp = fl.next();
            max = Integer.parseInt(temp);
        }
        calculateScore();
    }

    public boolean writeOut(String filePath) {
        try {
            File file = Paths.get(filePath).toFile();
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter b = new BufferedWriter(writer);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    b.write(Integer.toString(board[i][j].getValue()));
                    b.newLine();
                }
            }
            b.write(Integer.toString(max));
            b.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {
        TwentyFourtyEight t = new TwentyFourtyEight(false);
        t.printGameState();

        t.playTurn(1);
        t.printGameState();

        t.playTurn(1);
        t.printGameState();

        t.playTurn(2);
        t.printGameState();

        t.playTurn(2);
        t.printGameState();

        t.playTurn(3);
        t.printGameState();

        t.playTurn(3);
        t.printGameState();

        t.playTurn(1);
        t.printGameState();

        t.playTurn(3);
        t.printGameState();

        t.playTurn(4);
        t.printGameState();

        t.playTurn(4);
        t.printGameState();


        System.out.println();
        System.out.println();
        System.out.println(t.checkWinner());
        if (t.checkWinner() == 0) {
            System.out.println("Game is ongoing");
        }
    }

}
