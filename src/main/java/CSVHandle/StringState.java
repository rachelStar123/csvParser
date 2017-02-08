package CSVHandle;

/**
 * Created by Simba on 2017/2/7.
 */
public class StringState implements State {
    private CSVParser csvParser;

    public StringState(CSVParser csvParser) {
        this.csvParser = csvParser;
    }

    public void parse(Char ch) throws IllegalCSVFormatException {
        char singleChar = ch.getCh();
        if (singleChar == '\"' && ch.isQuote()) {
            throw new IllegalCSVFormatException("Illegal csv format: \" should be contained within double quotes");
        } else if (singleChar == '\n' || ch.isEnd()) {
            csvParser.newColumn();
            csvParser.newLine();
            csvParser.setState(csvParser.getStartState());
        } else if (singleChar == ',') {
            csvParser.newColumn();
            csvParser.setState(csvParser.getStartState());
        } else {
            csvParser.appendCellValue(singleChar);
        }
    }
}
