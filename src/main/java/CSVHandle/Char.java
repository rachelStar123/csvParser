package CSVHandle;

/**
 * Created by Simba on 2017/2/7.
 */
public class Char {
    private static int NORMAL = 1;
    private static int QUOTE = 2;
    private static int END = 3;

    private char ch;
    private int type;

    public Char(char ch) {
        this.ch = ch;
    }

    public Char(char ch, int type) {
        this.ch = ch;
        this.type = type;
    }

    public void setNormal() {
        type = NORMAL;
    }

    public void setEnd() {
        type = END;
    }

    public void setQuote() {
        type = QUOTE;
    }

    public boolean isNormal() {
        return this.type == NORMAL;
    }

    public boolean isQuote() {
        return this.type == QUOTE;
    }

    public boolean isEnd() {
        return this.type == END;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
