package rockPaperScissors;

/**
 * @author Trent Bennett CS-1410 Rock Paper Scissors game
 * 2015
 */

public class RockPaperScissors {
    private int win = 0;
    private int loss = 0;
    private int tie = 0;

    public static enum Score {
        WIN, LOSS, TIE;
    }

    public Score getScore(String player, String computer) {

        if (player.equalsIgnoreCase("ROCK")) {
            if (computer.equalsIgnoreCase("PAPER")) {
                loss++;
                return Score.LOSS;
            } else if (computer.equalsIgnoreCase("SCISSORS")) {
                win++;
                return Score.WIN;
            }
        } else if (player.equalsIgnoreCase("PAPER")) {

            if (computer.equalsIgnoreCase("SCISSORS")) {
                loss++;
                return Score.LOSS;
            } else if (computer.equalsIgnoreCase("ROCK")) {
                win++;
                return Score.WIN;
            }
        } else if (player.equalsIgnoreCase("SCISSORS")) {

            if (computer.equalsIgnoreCase("ROCK")) {
                loss++;
                return Score.LOSS;
            } else if (computer.equalsIgnoreCase("PAPER")) {
                win++;
                return Score.WIN;
            }

        }
        tie++;
        return Score.TIE;
    }

    public String getRandom() {
        double rand = Math.random();

        if (rand < Math.random()) {
            return "ROCK";
        } else if (rand < Math.random()) {
            return "PAPER";
        } else {
            return "SCISSORS";
        }
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
    }

    public int getTie() {
        return tie;
    }
}
