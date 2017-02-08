package CSVHandle;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simba on 2017/2/7.
 */
public class CSVParser {
    private State startState;
    private State stringState;
    private State quotationState;
    private State endQuotes;

    private State state;
    private List<List<String>> csvs;
    private List<String> currentLine;
    private StringBuilder cellValue;

    public CSVParser() {
        startState = new StartState(this);
        stringState = new StringState(this);
        quotationState = new QuotationState(this);
        endQuotes = new EndQuotesState(this);
        state = startState;
        csvs = new ArrayList<List<String>>();
    }

    public void clean() {
        state = startState;
        csvs = new ArrayList<List<String>>();
        currentLine = null;
        cellValue = null;
    }

    public List<List<String>> parse(String content) throws IllegalCSVFormatException {
        if (content == null) return null;
        content = content + '\0'; // append '\0' as Terminator
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            Char Ch = new Char(ch);
            if (ch == '\0') {
                Ch.setEnd();
            } else if (ch == '\r' && i < content.length() - 1 && content.charAt(i + 1) == '\n') {
                Ch.setCh('\n');
                Ch.setNormal();
                i++;
            } else {
                if (ch == '\"') {
                    if (i < content.length() - 1 && content.charAt(i + 1) == '\"') {
                        Ch.setNormal();
                        i++;
                    } else {
                        Ch.setQuote();
                    }
                } else {
                    Ch.setNormal();
                }
            }

            // 解析
            state.parse(Ch);
        }
        return csvs;
    }

    public void appendCellValue(char ch) {
        if (cellValue == null) {
            cellValue = new StringBuilder();
        }
        cellValue.append(ch);
    }

    public void newColumn() {
        if (currentLine == null) {
            currentLine = new ArrayList<String>();
        }
        if (cellValue == null) {
            currentLine.add(null);
        } else {
            currentLine.add(cellValue.toString());
            cellValue = null;
        }
    }

    public void newLine() {
        csvs.add(currentLine);
        currentLine = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getStartState() {
        return startState;
    }

    public State getStringState() {
        return stringState;
    }

    public State getQuotationState() {
        return quotationState;
    }

    public State getEndQuotes() {
        return endQuotes;
    }

    public static void main(String[] args) {
        String s = "123";
        s = s + "\0";
        System.out.println(s);
        System.out.println(s.length());

        StringBuilder st = new StringBuilder();
        String bbs = st.toString();
        System.out.println(bbs);
    }
}
