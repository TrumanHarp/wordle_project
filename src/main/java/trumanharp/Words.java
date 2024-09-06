package trumanharp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Words {
    private Vector<String> wds;
    public Words() {
        wds = new Vector<String>();
        Scanner infile = null;
        try {
            infile = new Scanner(new FileReader("./src/main/words.txt"));
        } catch (FileNotFoundException error) {
            System.out.println("words.txt not found");
            error.printStackTrace();
        }

        while(infile.hasNextLine()) {
            wds.addElement(infile.next());
        }

        infile.close();
    }

    public boolean findWord(String str) {
        if(wds.indexOf(str) != -1) return true;
        return false;
    }

    /* public void printWordsLol() {
        for(int i = 0; i < wds.size(); i++) {
            System.out.println(wds.elementAt(i));
        }
    } */

    public String getRandomWord() {
        int rand = new Random().nextInt(wds.size());
        return wds.elementAt(rand);
    }
}
