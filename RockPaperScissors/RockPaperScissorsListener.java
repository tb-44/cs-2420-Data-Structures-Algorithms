package rockPaperScissors;

/**
 * Trent Bennett
 * CS-1410
 * Rock Paper Scissors game
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RockPaperScissorsListener implements ActionListener {
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

	public RockPaperScissorsListener(JLabel playerLabel, JLabel computerLabel, JLabel gameLabel, JButton rockButton,
			JButton paperButton, JButton scissorsButton, JLabel winLabel, JLabel lossLabel, JLabel tieLabel) {

		this.game = new RockPaperScissors();
		this.playerLabel = playerLabel;
		this.computerLabel = computerLabel;
		this.gameLabel = gameLabel;
		this.rockButton = rockButton;
		this.paperButton = paperButton;
		this.scissorsButton = scissorsButton;
		this.winLabel = winLabel;
		this.lossLabel = lossLabel;
		this.tieLabel = tieLabel;
	}

	public void actionPerformed(ActionEvent e) {

		String player = game.getRandom();
		if (e.getSource() == rockButton) {
			player = "ROCK";
		} else if (e.getSource() == paperButton) {
			player = "PAPER";
		} else if (e.getSource() == scissorsButton) {
			player = "SCISSORS";
		}
		String computer = game.getRandom();

		playerLabel.setText("  You select " + player);
		playerLabel.setFont(new Font("Euphemia", Font.BOLD, 14));
		computerLabel.setText("Computer selects " + computer + "  ");
		computerLabel.setFont(new Font("Euphemia", Font.BOLD, 14));

		RockPaperScissors.Score score = game.getScore(player, computer);

		if (score == RockPaperScissors.Score.WIN) {
			gameLabel.setText("You win");
			gameLabel.setFont(new Font("Euphemia", Font.BOLD, 14));
		} else if (score == RockPaperScissors.Score.LOSS) {
			gameLabel.setText("Computer wins");
			gameLabel.setFont(new Font("Euphemia", Font.BOLD, 14));
		} else if (score == RockPaperScissors.Score.TIE) {
			gameLabel.setText("Tie");
			gameLabel.setFont(new Font("Euphemia", Font.BOLD, 14));
		}
		winLabel.setText(" Win: " + game.getWin());
		lossLabel.setText(" Loss: " + game.getLoss());
		tieLabel.setText(" Tie: " + game.getTie());
	}

}
