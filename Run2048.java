package org.cis1200.twentyfourtyeight;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class Run2048 implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.NORTH);


        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);


        // Game board
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        control_panel.add(status);
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("New Game");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        final JButton save = new JButton("Save Run");
        save.addActionListener(e -> board.save());
        control_panel.add(save);

        final JButton load = new JButton("Load Run");
        load.addActionListener(e -> board.load());
        control_panel.add(load);

        final JFrame instructions = new JFrame("Instructions");
        instructions.setLocation(250, 350);

        final JTextArea text = new JTextArea(
                "Welcome to 2048! \n" + "2048 is an easy and fun puzzle game. \n" +
                        "Even if you don't love numbers you will love this game. \n" +
                        "It is played on a 4x4 grid using W, A, S, D keys " +
                        "for up, left, down, and right " +
                        "respectively. \n" +
                        "Every time you press a key - all tiles slide. \n" +
                        "Tiles with the same value that bump into one-another are merged. \n" +
                        "Get to 2048 to win but you can keep playing after!",
                20, 20
        );



        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        instructions.add(text);
        instructions.pack();
        instructions.setVisible(true);
        // Start the game
        board.reset();
    }
}