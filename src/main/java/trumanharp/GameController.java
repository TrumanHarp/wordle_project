package trumanharp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GameController extends Controller{
    GameState gameState;
    GameController(GameState gs) {
        super("Game");
        gameState = gs;
        createBoxArray();
        createKeyArray();
        setup();
        changeKeyColors();
        getStage().setTitle("Wordle");
    }
    String darkestGrey = "2c2d2e";
    String darkGrey = "5a5c5e";
    String grey = "939699";
    String lightGrey = "e1e6eb";
    String yellow = "a1a115";
    String green = "3a913a";

    private String lastDirectory = "";
    public void setLastDirectory(String dir) {lastDirectory = dir;}
    public String getLastDirectory() {return lastDirectory;}

    @FXML private Button bMainMenu;
    @FXML private Button bSave;
    @FXML private Button bRestart;

    @FXML private Label b00;
    @FXML private Label b01;
    @FXML private Label b02;
    @FXML private Label b03;
    @FXML private Label b04;

    @FXML private Label b10;
    @FXML private Label b11;
    @FXML private Label b12;
    @FXML private Label b13;
    @FXML private Label b14;

    @FXML private Label b20;
    @FXML private Label b21;
    @FXML private Label b22;
    @FXML private Label b23;
    @FXML private Label b24;

    @FXML private Label b30;
    @FXML private Label b31;
    @FXML private Label b32;
    @FXML private Label b33;
    @FXML private Label b34;

    @FXML private Label b40;
    @FXML private Label b41;
    @FXML private Label b42;
    @FXML private Label b43;
    @FXML private Label b44;

    @FXML private Label b50;
    @FXML private Label b51;
    @FXML private Label b52;
    @FXML private Label b53;
    @FXML private Label b54;

    @FXML private Button bA;
    @FXML private Button bB;
    @FXML private Button bC;
    @FXML private Button bD;
    @FXML private Button bE;
    @FXML private Button bF;
    @FXML private Button bG;
    @FXML private Button bH;
    @FXML private Button bI;
    @FXML private Button bJ;
    @FXML private Button bK;
    @FXML private Button bL;
    @FXML private Button bM;
    @FXML private Button bN;
    @FXML private Button bO;
    @FXML private Button bP;
    @FXML private Button bQ;
    @FXML private Button bR;
    @FXML private Button bS;
    @FXML private Button bT;
    @FXML private Button bU;
    @FXML private Button bV;
    @FXML private Button bW;
    @FXML private Button bX;
    @FXML private Button bY;
    @FXML private Button bZ;

    @FXML private Button bEnter;
    @FXML private Button bDelete;
    @FXML private Label infoBox;

    private Label[] bArray;
    private Button[] kArray;

    @FXML AnchorPane endingPopupBackground;
    @FXML AnchorPane endingPopup;
    @FXML Label outcome;
    @FXML Label moreInfo;

    @FXML Button bNewGame;
    @FXML Button bShowStats;

    private void setup() {
        if(gameState.getGameInProgress() == false) {
            gameState.setGameInProgress(true);
            gameState.setChoosenWord((new Words()).getRandomWord());
            bArray[0].setStyle("-fx-border-color: #"+grey+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+darkestGrey+";");
            return;
        }
        for(int i = 0; i < gameState.getCurrentBoxRaw(); i++) {
            char ch = gameState.getCharAt(gameState.getBoxFromRaw(i)[0], gameState.getBoxFromRaw(i)[1]);
            char bch = gameState.getBckgAt(gameState.getBoxFromRaw(i)[0], gameState.getBoxFromRaw(i)[1]);
            bArray[i].setText(Character.toString(ch));
            switch (bch) {
                case 'B':
                    bArray[i].setStyle("-fx-border-color: #"+darkGrey+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+darkGrey+";");
                    break;
                case 'Y':
                    bArray[i].setStyle("-fx-border-color: #"+yellow+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+yellow+";");
                    break;
                case 'G':
                    bArray[i].setStyle("-fx-border-color: #"+green+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+green+";");
                    break;
                case 'E':
                    bArray[i].setStyle("-fx-border-color: #"+darkGrey+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+darkestGrey+";");
                    break;
                default:
                    break;
            }
        }
        if(gameState.getCurrentBoxRaw() < 30) bArray[gameState.getCurrentBoxRaw()].setStyle("-fx-border-color: #"+grey+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+darkestGrey+";");
        
    }

    private void createBoxArray() {
        bArray = new Label[30];
        bArray[0] = b00;
        bArray[1] = b01;
        bArray[2] = b02;
        bArray[3] = b03;
        bArray[4] = b04;

        bArray[5] = b10;
        bArray[6] = b11;
        bArray[7] = b12;
        bArray[8] = b13;
        bArray[9] = b14;

        bArray[10] = b20;
        bArray[11] = b21;
        bArray[12] = b22;
        bArray[13] = b23;
        bArray[14] = b24;

        bArray[15] = b30;
        bArray[16] = b31;
        bArray[17] = b32;
        bArray[18] = b33;
        bArray[19] = b34;

        bArray[20] = b40;
        bArray[21] = b41;
        bArray[22] = b42;
        bArray[23] = b43;
        bArray[24] = b44;

        bArray[25] = b50;
        bArray[26] = b51;
        bArray[27] = b52;
        bArray[28] = b53;
        bArray[29] = b54;
    }
    private void createKeyArray() {
        kArray = new Button[26];
        kArray[0] = bA;
        kArray[1] = bB;
        kArray[2] = bC;
        kArray[3] = bD;
        kArray[4] = bE;
        kArray[5] = bF;
        kArray[6] = bG;
        kArray[7] = bH;
        kArray[8] = bI;
        kArray[9] = bJ;
        kArray[10] = bK;
        kArray[11] = bL;
        kArray[12] = bM;
        kArray[13] = bN;
        kArray[14] = bO;
        kArray[15] = bP;
        kArray[16] = bQ;
        kArray[17] = bR;
        kArray[18] = bS;
        kArray[19] = bT;
        kArray[20] = bU;
        kArray[21] = bV;
        kArray[22] = bW;
        kArray[23] = bX;
        kArray[24] = bY;
        kArray[25] = bZ;
    }
    
    private void changeKeyColors() {
        for(int i = 0; i < 26; i++) {
            switch (gameState.getKeyColor(i)) {
                case 'B':
                    
                    kArray[i].setStyle(kArray[i].getStyle()+"-fx-background-color: #"+darkGrey+";");
                    kArray[i].setTextFill(Color.web(grey));
                    break;
                case 'E':
                    kArray[i].setStyle(kArray[i].getStyle()+"-fx-background-color: #"+grey+";");
                    kArray[i].setTextFill(Color.BLACK);
                    break;
                case 'G':
                    kArray[i].setStyle(kArray[i].getStyle()+"-fx-background-color: #"+green+";");
                    kArray[i].setTextFill(Color.BLACK);
                    break;
                case 'Y':
                    kArray[i].setStyle(kArray[i].getStyle()+"-fx-background-color: #"+yellow+";");
                    kArray[i].setTextFill(Color.BLACK);
                    break;
                default:
                    break;
            }
            
            
        }
    }

    @FXML private void goMainMenu(ActionEvent event) {
        if(!(event.getSource() instanceof Button)) return;
        if(gameState.getGameInProgress() == false) {
            clearBoard();
        }
        MainMenuController mainMenuController = new MainMenuController(gameState);
        mainMenuController.showStage();
        closeStage();
    }

    @FXML private void save(ActionEvent event) {

        int outcome = saveHelper(event);
        if(outcome == 0) {
            infoBox.setText("Saving failed...");
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
            //System.out.println("Cant save at that file");
            error.printStackTrace();
            return 0;
        }

        return 1;
    }

    @FXML private void keyboardPress(ActionEvent event) {
        if(!(event.getSource() instanceof Button)) return;
        if(gameState.getGameInProgress() == false) return;
        emptyText();
        Button btn = (Button) event.getSource();
        char key = btn.getText().charAt(0);
        //System.out.println("pressing key: "+key);
        if(gameState.getCurrentBoxRaw() >= 30) {
            //infoBox.setText("Game is Over");
            return;
        }
        if(gameState.getCharAt(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1]) == 0) {
            gameState.setCharAt(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1], key);
        }
        if(gameState.getCurrentBox()[1] < 4) {
            gameState.setCurrentBox(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1]+1);
        }
        updateBoard();
    }

    @FXML private void deleteKey(ActionEvent event) {
        if(!(event.getSource() instanceof Button)) return;
        if(gameState.getGameInProgress() == false) return;
        emptyText();
        if(gameState.getCurrentBox()[1] < 1) {
            infoBox.setText("Nothing to Delete");
            return;
        }
        char zd = 0;
        if(gameState.getCurrentBox()[1] < 4 || (gameState.getCurrentBox()[1] == 4 && (gameState.getCharAt(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1]) == zd))) {
            gameState.setCharAt(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1]-1, zd);
            gameState.setCurrentBox(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1]-1);
        } else { //This is for if were on the 5th box and its full
            gameState.setCharAt(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1], zd);
            //gameState.setCurrentBox(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1]);
        }
        updateBoard();
    }

    @FXML private void enterKey(ActionEvent event) {
        int gameOutcome = 0; //0 if we keep playing, 1 for a loss, 2 for a win;
        emptyText();
        if(!(event.getSource() instanceof Button)) return;
        if(gameState.getGameInProgress() == false) return;
        char zd = 0;
        if(gameState.getCurrentBox()[1] < 4 || gameState.getCharAt(gameState.getCurrentBox()[0], gameState.getCurrentBox()[1]) == zd) {
            infoBox.setText("Please type a full word");
            return;
        }
        String sToCheck = "";
        for(int i = 0; i < 5; i++) {
            sToCheck += Character.toString(gameState.getCharAt(gameState.getCurrentBox()[0], i));
        }
        if(!((new Words()).findWord(sToCheck.toLowerCase()))) {
            infoBox.setText("Invalid Word Entered");
            return;
        }
        
        //System.out.println("entering..");
        //System.out.println(wordOutcome());
        String outcome = wordOutcome();
        Scanner outScan = new Scanner(outcome);
        
        int row = gameState.getCurrentBox()[0];
        if(outcome.equals("G G G G G ")|| outcome.equals("G G G G G")) {
            gameOutcome = 2;
            gameState.setGameInProgress(false);
            for (int i = 0; i < 5; i++) {
                gameState.setBckgAt(row, i, outScan.next().charAt(0));
            }
            //System.out.println("Won game");
        } else if(gameState.getCurrentBox()[0] >= 5) {
            gameOutcome = 1;
            gameState.setGameInProgress(false);
            for (int i = 0; i < 5; i++) {
                gameState.setBckgAt(row, i, outScan.next().charAt(0));
            }
            //System.out.println("Lost game");
        } else {
            
            gameState.setCurrentBox(gameState.getCurrentBox()[0]+1, 0);
            for (int i = 0; i < 5; i++) {
                gameState.setBckgAt(row, i, outScan.next().charAt(0));
            }
        }
        gameState.calcKeyColors();
        changeKeyColors();
        outScan.close();
        updateBoard();
        if(gameOutcome == 1) {
            onLoss();
        } else if(gameOutcome == 2) {
            onWin(row);
        }
    }


    private String wordOutcome() { //will output a result string like "B Y Y G G "
        String retString = "";
        for(int i = 0; i < 5; i++) {
            String s = "B";
            //System.out.println("does "+gameState.getChoosenWord()+" contain "+ Character.toString(gameState.getCharAt(gameState.getCurrentBox()[0], i)));
            if(gameState.getChoosenWord().toUpperCase().contains(Character.toString(gameState.getCharAt(gameState.getCurrentBox()[0], i)))) { //this hurt to write
                s = "Y";
            }
            //System.out.println("does "+gameState.getChoosenWord().toUpperCase().charAt(i)+" equal "+gameState.getCharAt(gameState.getCurrentBox()[0], i));
            if(gameState.getChoosenWord().toUpperCase().charAt(i) == gameState.getCharAt(gameState.getCurrentBox()[0], i)) {
                s = "G";
            }
            retString+=(s+" ");
        }
        return retString;
    }

    public Boolean isLetterInWord(char ch, String word) {
        return word.contains(Character.toString(ch));
    }

    private void updateBoard() {
        for(int i = 0; i < 30; i++) {
            char ch = gameState.getCharAt(gameState.getBoxFromRaw(i)[0], gameState.getBoxFromRaw(i)[1]);
            char bch = gameState.getBckgAt(gameState.getBoxFromRaw(i)[0], gameState.getBoxFromRaw(i)[1]);
            bArray[i].setText(Character.toString(ch));
            switch (bch) {
                case 'B':
                    bArray[i].setStyle("-fx-border-color: #"+darkGrey+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+darkGrey+";");
                    break;
                case 'Y':
                    bArray[i].setStyle("-fx-border-color: #"+yellow+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+yellow+";");
                    break;
                case 'G':
                    bArray[i].setStyle("-fx-border-color: #"+green+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+green+";");
                    break;
                case 'E':
                    bArray[i].setStyle("-fx-border-color: #"+darkGrey+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+darkestGrey+";");
                    break;
                default:
                    break;
            }
        }
        if(gameState.getCurrentBoxRaw() < 30 && gameState.getGameInProgress()) bArray[gameState.getCurrentBoxRaw()].setStyle("-fx-border-color: #"+grey+";"+"-fx-border-width: 3;"+"-fx-background-color: #"+darkestGrey+";");
    }

    private void clearBoard() {
        char zd = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                gameState.setCharAt(i, j, zd);
                gameState.setBckgAt(i, j, 'E');
            }
        }
        gameState.setCurrentBox(0, 0);
        updateBoard();
        gameState.calcKeyColors();
        changeKeyColors();
    }

    private void onLoss() {
        outcome.setText("YOU LOSS");
        moreInfo.setText("The correct word was "+gameState.getChoosenWord());
        endingPopupBackground.setVisible(true);
        endingPopup.setVisible(true);
        endingPopup.setDisable(false);
        bSave.setDisable(true);
        bRestart.setDisable(true);
        bMainMenu.setDisable(true);

        gameState.setCurrentWinStreak(0);
        gameState.setGamesPlayed(gameState.getGamesPlayed()+1);
    }

    private void onWin(int row) {
        outcome.setText("YOU WON!");
        moreInfo.setText("It took you "+(row+1)+" guesses.");
        endingPopupBackground.setVisible(true);
        endingPopup.setVisible(true);
        endingPopup.setDisable(false);
        bSave.setDisable(true);
        bRestart.setDisable(true);
        bMainMenu.setDisable(true);

        gameState.setGamesPlayed(gameState.getGamesPlayed()+1);
        gameState.setGamesWon(gameState.getGamesWon()+1);
        gameState.setCurrentWinStreak(gameState.getCurrentWinStreak()+1);
        if(gameState.getCurrentWinStreak() > gameState.getMaxWinStreak()) {
            gameState.setMaxWinStreak(gameState.getCurrentWinStreak());
        }
        gameState.AddWinToDistribution(row+1); //row+1 might not be right
    }

    @FXML private void restart() {
        gameState.setChoosenWord((new Words()).getRandomWord());
        clearBoard();
    }

    @FXML private void nextGame() {
        gameState.setChoosenWord((new Words()).getRandomWord());
        bSave.setDisable(false);
        bRestart.setDisable(false);
        bMainMenu.setDisable(false);
        gameState.setGameInProgress(true);
        clearBoard();
        endingPopupBackground.setVisible(false);
        endingPopup.setVisible(false);
        endingPopup.setDisable(true);
    }

    /* @FXML private void printGS() {
        System.out.println(gameState.saveGameStateToString());
    } */

    private void emptyText() {
        infoBox.setText("");
    }

    @FXML private void showStats() {
        StatController statController = new StatController(gameState);
        statController.showStage();
    }
}