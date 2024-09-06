package trumanharp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GameState {
    private String wordleCheck;
    private int gamesPlayed;
    private int gamesWon;
    private int currentWinStreak;
    private int maxWinStreak;
    private boolean gameInProgress;
    private String choosenWord;

    private HashMap<Integer, CellData> cellHash;
    private int[] currentBox;
    private int[] winDistribution;
    private char[] keyboardColors;


    public GameState() {
        wordleCheck = "TrumanHarpWordle";
        gamesPlayed = gamesWon = currentWinStreak = maxWinStreak = 0;
        gameInProgress = false;
        choosenWord = "00000";
        cellHash = new HashMap<Integer, CellData>(30);
        for(int i = 0; i < 30; i++) {
            cellHash.put(i, new CellData());
        }
        currentBox = new int[] {0, 0};
        winDistribution = new int[] {0, 0, 0, 0, 0, 0};
        keyboardColors = new char[26];
        for(int i = 0; i < 26; i++) {
            keyboardColors[i] = 'E';
        }
    }

    public String nextLineToken(Scanner file) throws NoSuchElementException {
            String line = file.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line);
            return tokenizer.nextToken();
    }

    public String nextToken(Scanner file) throws NoSuchElementException {
        return file.next();
    }

    public int readFromFile(String filepath) { //return 0 on fail, 1 on success
        Scanner infile = null;
        try {
            infile = new Scanner(new FileReader(filepath));
        } catch (FileNotFoundException error) {
            //System.out.println("File not foundt at: "+filepath);
            error.printStackTrace();
            return 0;
        }

        try {
            String s = nextLineToken(infile);
            //System.out.println(s);
            if(!s.equals("TrumanHarpWordle")) {
                System.out.println("bad read");
                return 0; 
            }
            //System.out.println("good read");
            setGamesPlayed(Integer.parseInt(nextLineToken(infile)));
            setGamesWon(Integer.parseInt(nextLineToken(infile)));
            setCurrentWinStreak(Integer.parseInt(nextLineToken(infile)));
            setMaxWinStreak(Integer.parseInt(nextLineToken(infile)));
            setGameInProgress(Boolean.parseBoolean(nextLineToken(infile)));
            String newWord = nextLineToken(infile);
            Words words = new Words();
            if(!(words.findWord(newWord))) throw new NoSuchElementException("Tried to read an invalid wordle word");
            else setChoosenWord(newWord);
            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 5; j++) {
                    char ch = nextToken(infile).charAt(0);
                    if(!Character.isAlphabetic(ch)) {
                        if(ch == '0') {
                            ch = 0;
                        } else throw new NumberFormatException("Tried to read in an invalid character in the words");
                    }
                    setCharAt(i, j, ch);
                }
            }
            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 5; j++) {
                    char ch = nextToken(infile).charAt(0);
                    if(!Character.isAlphabetic(ch)) throw new NoSuchElementException("Read a non alpha character for bckg.");
                    if(ch != 'B' && ch != 'Y' && ch != 'G' && ch != 'E') throw new NoSuchElementException("Read a non background letter.");
                    setBckgAt(i, j, ch);
                }
            }
            setCurrentBox(Integer.parseInt(nextToken(infile)), Integer.parseInt(nextToken(infile)));
            for(int i = 0; i < 6; i++) {
                SetWinDistribution(i, Integer.parseInt(nextToken(infile)));
            }
            
        } catch (NoSuchElementException|NumberFormatException error) {
            //System.out.println("Save file is not correctly formatted.");
            error.printStackTrace();
            return 0;
        }

        infile.close();
        calcKeyColors();
        //System.out.println(saveGameStateToString());
        return 1;
    }

    public String saveGameStateToString() {
        String ret = "";
        ret+="TrumanHarpWordle\n";
        ret+=(getGamesPlayed()+"\n");
        ret+=(getGamesWon()+"\n");
        ret+=(getCurrentWinStreak()+"\n");
        ret+=(getMaxWinStreak()+"\n");
        ret+=(getGameInProgress()+"\n");
        ret+=(getChoosenWord()+"\n");
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                char ch = getCharAt(i, j);
                if(ch == 0) ch = '0';
                ret+=(ch+" ");
            }
            ret+="\n";
        }
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                ret+=(getBckgAt(i, j)+" ");
            }
            ret+="\n";
        }
        ret+=(getCurrentBox()[0] + " " + getCurrentBox()[1]+"\n");
        for(int i = 1; i <= 6; i++) {
            ret+=(getNumWinsAtGuess(i)+" ");
        }
        ret+="\n";
        return ret;
    }

    private void printGameState() {
        System.out.println(wordleCheck);
        System.out.println(getGamesPlayed());
        System.out.println(getGamesWon());
        System.out.println(getCurrentWinStreak());
        System.out.println(getMaxWinStreak());
        System.out.println(getGameInProgress());
        System.out.println(getChoosenWord());
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(getCharAt(i, j)+" ");
            }
            System.out.println("");
        }
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(getBckgAt(i, j)+" ");
            }
            System.out.println("");
        }
        System.out.println(getCurrentBox()[0] + " " + getCurrentBox()[1]);
        for(int i = 1; i <= 6; i++) {
            System.out.print(getNumWinsAtGuess(i)+" ");
        }
        System.out.println("");
        
    }

    public void calcKeyColors() {
        char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for(int i = 0; i < 26; i++) {
            char newBg ='E';
            for(int j = 0; j < 30; j++) {
                int[] box = getBoxFromRaw(j);
                char ch = getCharAt(box[0], box[1]);
                char bg = getBckgAt(box[0], box[1]);
                if(ch == alpha[i]) {
                    if(bg == 'B') {
                        newBg = 'B';
                    } else if(bg == 'G') {
                        newBg = 'G';
                    } else if(bg == 'Y' && newBg != 'G') { //dont let yellow override green
                        newBg = 'Y';
                    }
                }
            }
            keyboardColors[i] = newBg;
        }
    }

    public void setGamesWon(Integer g) {gamesWon = g;}
    public Integer getGamesWon() {return gamesWon;}

    public void setGamesPlayed(Integer g) {gamesPlayed = g;}
    public Integer getGamesPlayed() {return gamesPlayed;}

    public void setCurrentWinStreak(Integer g) {currentWinStreak = g;}
    public Integer getCurrentWinStreak() {return currentWinStreak;}

    public void setMaxWinStreak(Integer g) {maxWinStreak = g;}
    public Integer getMaxWinStreak() {return maxWinStreak;}

    public void setGameInProgress(Boolean p) {gameInProgress = p;}
    public Boolean getGameInProgress() {return gameInProgress;}

    public void setChoosenWord(String cw) {choosenWord = cw;}
    public String getChoosenWord() {return choosenWord;}

    public void setCharAt(Integer row, Integer col, char ch) {
        cellHash.get(getRawFromBox(row, col)).setLetter(ch);
    }
    public char getCharAt(Integer row, Integer col) {
        return cellHash.get(getRawFromBox(row, col)).getLetter();
    }

    public void setBckgAt(Integer row, Integer col, char ch) {
        cellHash.get(getRawFromBox(row, col)).setColor(ch);
    }
    public char getBckgAt(Integer row, Integer col) {
        return cellHash.get(getRawFromBox(row, col)).getColor();
    }
        
    public void setCurrentBox(Integer row, Integer col) {
        currentBox[0] = row;
        currentBox[1] = col;
    }
    public int[] getCurrentBox()  {return currentBox;}
    public int getCurrentBoxRaw() {
        return currentBox[0]*5 + currentBox[1];
    }
    public int[] getBoxFromRaw(int raw) {
        int[] ret = new int[2];
        ret[0] = raw/5;
        ret[1] = raw%5;
        return ret;
    }
    private int getRawFromBox(int row, int col) {return row*5 + col;}

    public void SetWinDistribution(Integer index, Integer wins) {winDistribution[index] = wins;}
    public void AddWinToDistribution(Integer numGuess) {winDistribution[numGuess-1]++;}
    public int[] getWinDistrubtion() {return winDistribution;}
    public int getNumWinsAtGuess(int numGuess) {return winDistribution[numGuess-1];} 
    public void clearKeyColors() {
        for(int i = 0; i < 26; i++) {
            keyboardColors[i] = 'E';
        }
    }
    public char getKeyColor(int index) {
        return keyboardColors[index];
    }
}
