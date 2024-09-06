package trumanharp;

public class CellData {
    private Character letter;
    private Character color;
    public CellData() {
        letter = 0;
        color = 'E';
    }
    public CellData(Character l, Character c) {
        letter = l;
        color = c;
    }

    public Character getLetter() {return letter;}
    public void setLetter(Character c) {letter = c;}

    public Character getColor() {return color;}
    public void setColor(Character c) {color = c;}
}
