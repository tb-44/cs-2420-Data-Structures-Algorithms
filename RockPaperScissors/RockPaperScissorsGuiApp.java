package rockPaperScissors;

/**
 * Trent Bennett
 * CS-1410
 * Rock Paper Scissors game
 */

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class RockPaperScissorsGuiApp {
    RockPaperScissors game;

    JLabel playerLabel;
    JLabel computerLabel;
    JLabel gameLabel;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;

    JLabel winLabel;
    JLabel lossLabel;
    JLabel tieLabel;

    public static void main(String[] args) {

        JFrame frame = gameFrame();

        JLabel playerLabel = new JLabel("", JLabel.CENTER);
        JLabel computerLabel = new JLabel("", JLabel.CENTER);
        JLabel gameLabel = gameLabel();

        JButton rockButton = rockButton();
        JButton paperButton = paperButton();
        JButton scissorsButton = scissorsButton();

        JLabel winLabel = new JLabel("Wins: 0", JLabel.CENTER);
        winLabel.setFont(new Font("Euphemia", Font.BOLD, 14));

        JLabel lossLabel = new JLabel("Losses: 0", JLabel.CENTER);
        lossLabel.setFont(new Font("Euphemia", Font.BOLD, 14));

        JLabel tieLabel = new JLabel("Ties: 0", JLabel.CENTER);
        tieLabel.setFont(new Font("Euphemia", Font.BOLD, 14));

        ActionListener listener = new RockPaperScissorsListener(playerLabel, computerLabel, gameLabel, rockButton,
                paperButton, scissorsButton, winLabel, lossLabel, tieLabel);

        rockButton.addActionListener(listener);
        paperButton.addActionListener(listener);
        scissorsButton.addActionListener(listener);

        JPanel border = borderPanel(playerLabel, computerLabel, gameLabel);
        JPanel buttonPanel = buttonPanel(rockButton, paperButton, scissorsButton);
        JPanel scoreboard = scoreBoard();

        scoreboard.add(winLabel);
        scoreboard.add(lossLabel);
        scoreboard.add(tieLabel);

        frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(border, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(scoreboard, BorderLayout.SOUTH);

        frame.setSize(570, 365);
        frame.setVisible(true);
    }

    private static JFrame gameFrame() {
        JFrame frame = new JFrame("Rock Paper Scissors Game");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.getContentPane().setForeground(Color.BLACK);
        frame.getContentPane().setFont(new Font("Euphemia", Font.BOLD, 12));
        frame.setBackground(new Color(220, 220, 220));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private static JPanel borderPanel(JLabel playerLabel, JLabel computerLabel, JLabel gameLabel) {
        JPanel border = new JPanel();
        border.setLayout(new BorderLayout());
        border.add(playerLabel, BorderLayout.WEST);
        border.add(computerLabel, BorderLayout.EAST);
        border.add(gameLabel, BorderLayout.SOUTH);
        return border;
    }

    private static JPanel scoreBoard() {
        JPanel scoreboard = new JPanel();
        scoreboard.setBackground(Color.GRAY);
        scoreboard.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        scoreboard.setLayout(new FlowLayout());
        return scoreboard;
    }

    private static JPanel buttonPanel(JButton rockButton, JButton paperButton, JButton scissorsButton) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(244, 164, 96));
        buttonPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        return buttonPanel;
    }

    private static JButton scissorsButton() {

        JButton scissorsButton = new JButton(" SCISSORS ");
        scissorsButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        scissorsButton.setBackground(Color.GRAY);
        scissorsButton.setFont(new Font("Euphemia", Font.BOLD, 12));
        scissorsButton.setForeground(new Color(255, 255, 255));
        scissorsButton.setIcon(
                new ImageIcon(RockPaperScissorsGuiApp.class.getResource("/rockPaperScissors/images/scissors.jpg")));
        return scissorsButton;
    }

    private static JButton paperButton() {

        JButton paperButton = new JButton(" PAPER ");
        paperButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        paperButton.setBackground(Color.GRAY);
        paperButton.setFont(new Font("Euphemia", Font.BOLD, 12));
        paperButton.setForeground(new Color(255, 255, 255));
        paperButton.setIcon(
                new ImageIcon(RockPaperScissorsGuiApp.class.getResource("/rockPaperScissors/images/Paper.jpg")));
        return paperButton;
    }

    private static JButton rockButton() {

        JButton rockButton = new JButton(" ROCK ");
        rockButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        rockButton.setBackground(Color.GRAY);
        rockButton.setFont(new Font("Euphemia", Font.BOLD, 12));
        rockButton.setForeground(new Color(255, 255, 255));
        rockButton.setIcon(
                new ImageIcon(RockPaperScissorsGuiApp.class.getResource("/rockPaperScissors/images/rock.png")));
        return rockButton;
    }

    private static JLabel gameLabel() {

        JLabel gameLabel = new JLabel("ROCK PAPER SCISSORS - Make your selection to begin!", JLabel.CENTER);
        gameLabel.setOpaque(true);
        gameLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        gameLabel.setBackground(new Color(192, 192, 192));
        gameLabel.setFont(new Font("Euphemia", Font.BOLD, 14));
        return gameLabel;
    }
}