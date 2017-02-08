package CSVHandle;

/**
 * Created by Simba on 2017/2/7.
 */
public class StartState implements State {
    private CSVParser csvParser;

    public StartState(CSVParser csvParser) {
        this.csvParser = csvParser;
    }

    public void parse(Char ch) throws IllegalCSVFormatException {
        char singleChar = ch.getCh();
        if (singleChar == '\"' && ch.isQuote()) {
            csvParser.setState(csvParser.getQuotationState());
        } else if (singleChar == '\n' || ch.isEnd()) { // '\n' is newline in java
            csvParser.newColumn();
            csvParser.newLine();
        } else if (singleChar == ',') {
            csvParser.newColumn();
        } else {
            csvParser.appendCellValue(singleChar);
            csvParser.setState(csvParser.getStringState());
        }
    }
}
