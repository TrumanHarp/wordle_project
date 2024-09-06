package trumanharp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.*;

public class MainMenuController extends Controller{
    
    @FXML Label wordle;
    @FXML Button newSave;
    @FXML Button loadSave;
    @FXML Button cont;
    @FXML Button saveExit;
    @FXML Label infoBox;

    private GameState gameState;
    private String lastDirectory = "";
    public void setLastDirectory(String dir) {lastDirectory = dir;}
    public String getLastDirectory() {return lastDirectory;}

    public MainMenuController() {
        super("MainMenu");
        getStage().setTitle("Wordle");
        gameState = new GameState();
        cont.setVisible(false);
        saveExit.setVisible(false);
    }

    public MainMenuController(GameState gs) {
        super("MainMenu");
        getStage().setTitle("Wordle");
        gameState = gs;
        if(gameState.getGameInProgress()) {
            cont.setVisible(true);
            saveExit.setVisible(true);
        }
    }
    
    
    @FXML private void createNew(ActionEvent event) {
        if(!(event.getSource() instanceof Button)) return;
        GameController gameController = new GameController(new GameState());
        gameController.showStage();
        closeStage();
    }
    @FXML private void openExisting(ActionEvent event) {
        int success = openExistingHelper(event);
        if(success == 0) {
            infoBox.setText("Could not open or read file at "+getLastDirectory());
        } else {
            closeStage();
        }
    }
    private int openExistingHelper(ActionEvent event) {
        if(!(event.getSource() instanceof Button)) return 0 ;
        Stage fileStage = new Stage();
        FileChooser fC = new FileChooser();
        fC.setTitle("Open Save");
        if(getLastDirectory() != "") fC.setInitialDirectory(new File(getLastDirectory()));
        fC.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        File file = fC.showOpenDialog(fileStage);
        if(file == null) return 0;
        System.out.println(file.getPath());
        setLastDirectory(file.getParent());
        int readSuccess = gameState.readFromFile(file.getPath());
        if(readSuccess == 0) return 0;
        GameController gameController = new GameController(gameState);
        gameController.showStage();
        gameController.setLastDirectory(getLastDirectory());
        closeStage();
        return 1;
    }

    @FXML private void cont(ActionEvent event) {
        if(!(event.getSource() instanceof Button)) return;
        if(gameState == null) {
            infoBox.setText("Could not continue.");
            return;
        }
        GameController gameController = new GameController(gameState);
        gameController.showStage();
        closeStage();
    }
    
    @FXML private void saveExit(ActionEvent event) {
        int outcome = saveHelper(event);
        if(outcome == 0) {
            infoBox.setText("Could not save file there.");
        } else {
            closeStage();
        }
    }
    
    private int saveHelper(ActionEvent event) {
        if(!(event.getSource() instanceof Button)) return 0 ;
        Stage fileStage = new Stage();
        FileChooser fC = new FileChooser();
        fC.setTitle("Choose Save File");
        if(getLastDirectory() != "") fC.setInitialDirectory(new File(getLastDirectory()));
        fC.setInitialFileName("HarpWordle.txt");
        fC.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        File file = fC.showSaveDialog(fileStage);
        if(file == null) return 0;
        try {
            FileWriter fWriter = new FileWriter(file);
            fWriter.write(gameState.saveGameStateToString());
            fWriter.close();
        } catch(IOException error) {
            System.out.println("Cant save at that file");
            error.printStackTrace();
            return 0;
        }

        return 1;
    }
}
