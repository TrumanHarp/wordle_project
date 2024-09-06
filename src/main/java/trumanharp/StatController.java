package trumanharp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatController extends Controller {
    private GameState gameState;
    public StatController(GameState gs) {
        super("Stat");
        gameState = gs;
        initStats();
        getStage().setTitle("Statistics");
    }
    @FXML Label l1;
    @FXML Label l2;
    @FXML Label l3;
    @FXML Label l4;
    @FXML Label l5;
    @FXML Label l6;

    @FXML Label played;
    @FXML Label win;
    @FXML Label streak;
    @FXML Label max;

    private void initStats() {
        l1.setText(Integer.toString(gameState.getNumWinsAtGuess(1)));
        l2.setText(Integer.toString(gameState.getNumWinsAtGuess(2)));
        l3.setText(Integer.toString(gameState.getNumWinsAtGuess(3)));
        l4.setText(Integer.toString(gameState.getNumWinsAtGuess(4)));
        l5.setText(Integer.toString(gameState.getNumWinsAtGuess(5)));
        l6.setText(Integer.toString(gameState.getNumWinsAtGuess(6)));

        played.setText(Integer.toString(gameState.getGamesPlayed()));
        win.setText(Integer.toString(gameState.getGamesWon()));
        streak.setText(Integer.toString(gameState.getCurrentWinStreak()));
        max.setText(Integer.toString(gameState.getMaxWinStreak()));
    }

    @FXML private void closeStats() {
        closeStage();
    }
}
