package trumanharp;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.showStage();
    }

    public static void main(String[] args) {
        launch();
    }

}