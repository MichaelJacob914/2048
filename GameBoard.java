package org.cis1200.twentyfourtyeight;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Map<String, Color> colorMap = new TreeMap<>();

    private TwentyFourtyEight ttt; // model for the game
    private JLabel status; // current status text


    // Game constants
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 700;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {

        colorMap.put("Background", new Color(255,248,229));
        colorMap.put("Empty", new Color(187,173,160));
        colorMap.put("0", new Color(204, 192, 179));
        colorMap.put("2", new Color(250, 248, 239));
        colorMap.put("4", new Color(237, 224, 200));
        colorMap.put("8", new Color(255, 181, 125));
        colorMap.put("16", new Color(245, 149, 99));
        colorMap.put("32", new Color(246, 124, 95));
        colorMap.put("64", new Color(246, 94, 59));
        colorMap.put("128", new Color(237, 207, 114));
        colorMap.put("256", new Color(237, 204, 97));
        colorMap.put("512", new Color(237, 200, 80));
        colorMap.put("1024", new Color(237, 197, 63));
        colorMap.put("2048", new Color(237, 194, 46));
        colorMap.put("TITLE", new Color(118,110,101));

        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(colorMap.get("Background")));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        JButton button = new JButton("New Game");

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ttt.reset();
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });

        JButton save = new JButton("Save");

        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("TESTER 1");
                save();
            }
        });

        JButton load = new JButton("Load");

        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("TESTER 2");
                load();
            }
        });


        ttt = new TwentyFourtyEight(false); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char p = e.getKeyChar();
                if (p == 'w') {
                    ttt.playTurn(3);
                } else if (p == 'a') {
                    ttt.playTurn(1);
                } else if (p == 's') {
                    ttt.playTurn(4);
                } else if (p == 'd') {
                    ttt.playTurn(2);
                }

                // updates the model given the coordinates of the mouseclick
                //ttt.playTurn(p.x / 100, p.y / 100);

                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        ttt.reset();
        status.setText("");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {

        int winner = ttt.checkWinner();
        if (winner == 1) {
            status.setText("You Reached 2048!");
        } else if (winner == 2) {
            status.setText("Game Over");
        }
    }

    public void colorMapUpdate(Tile[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (colorMap.get(board[i][j].getColor()) == null) {
                    int r = (int) (int)(Math.random() * 255);
                    int g = (int) (int)(Math.random() * 255);
                    int b = (int) (int)(Math.random() * 255);
                    colorMap.put(board[i][j].getColor(), new Color(r,g,b));
                }
            }
        }
    }

    public void save() {
        ttt.writeOut("2048.txt");
        repaint();
        requestFocusInWindow();

    }
    public void load() {
        ttt.readIn("2048.txt");
        repaint();
        requestFocusInWindow();

    }
    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        colorMapUpdate(ttt.getBoard());

        // Draws board grid
        g.setColor(colorMap.get("Background"));
        g.fillRect(0,0,500,700);
        g.setColor(colorMap.get("Empty"));
        g.fillRoundRect(20,150,460,480,10,10);
        g.setFont(new Font("Clean Sans", Font.BOLD, 70));
        g.setColor(colorMap.get("TITLE"));
        g.drawString("2048", 20, 100);

        g.setColor(colorMap.get("0"));
        g.fillRoundRect(295,20,70,55, 10, 10);
        g.fillRoundRect(370,20,110,55, 10, 10);
        g.setFont(new Font("Clean Sans", Font.BOLD, 15));
        g.setColor(colorMap.get("Background"));
        g.drawString("Score", 307, 40);
        g.drawString("Best", 410, 40);
        g.setFont(new Font("Clean Sans", Font.BOLD, 22));
        g.setColor(colorMap.get("2"));




        int x = 0;
        int y = 0;
        if (ttt.getScore() >= 1000) {
            x = 25;
        } else if (ttt.getScore() >= 100) {
            x = 17;
        } else if (ttt.getScore() >= 10) {
            x = 5;
        }

        if (ttt.getBest() >= 1000) {
            y = 25;
        } else if (ttt.getBest() >= 100) {
            y = 17;
        } else if (ttt.getBest() >= 10) {
            y = 8;
        }

        g.drawString(Integer.toString(ttt.getScore()), 322 - x, 65);
        g.drawString(Integer.toString(ttt.getBest()), 420 - y, 65);

        g.setFont(new Font("Clean Sans", Font.BOLD, 40));
        // Draws X's and O's
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.setColor(colorMap.get(ttt.getCell(i,j).getColor()));
                g.fillRoundRect(i * 100 + 50,j * 100 + 200, 90,90, 10, 10);
                g.setColor(colorMap.get("TITLE"));

                if (ttt.getCell(i,j).getValue() > 1000) {
                    g.setFont(new Font("Clean Sans", Font.BOLD, 35));
                    g.drawString(ttt.getCell(i,j).getColor(), (i * 100) + 47, (j * 100) + 260);
                    g.setFont(new Font("Clean Sans", Font.BOLD, 40));
                } else if (ttt.getCell(i,j).getValue() > 100) {
                    g.drawString(ttt.getCell(i,j).getColor(), (i * 100) + 57, (j * 100) + 260);
                } else if (ttt.getCell(i,j).getValue() > 10) {
                    g.drawString(ttt.getCell(i,j).getColor(), (i * 100) + 67, (j * 100) + 260);
                } else {
                    g.drawString(ttt.getCell(i, j).getColor(), (i * 100) + 80, (j * 100) + 260);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
