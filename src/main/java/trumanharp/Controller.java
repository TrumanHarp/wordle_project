package trumanharp;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    private Stage stage;
    private String fxmlString;

    public Controller(String fxmlName) {
        fxmlString = fxmlName;
        stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlString+".fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch(IOException error) {}
    }

    public void showStage() {
        stage.show();
        stage.setResizable(false);
    }
    public void closeStage() {
        stage.close();
    }

    public Stage getStage() {return stage;}
}
