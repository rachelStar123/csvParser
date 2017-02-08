package CSVHandle;

/**
 * Created by Simba on 2017/2/7.
 */
public class QuotationState implements State {
    private CSVParser csvParser;

    public QuotationState(CSVParser csvParser) {
        this.csvParser = csvParser;
    }

    public void parse(Char ch) throws IllegalCSVFormatException {
        char singleChar = ch.getCh();
        if (singleChar == '\"' && ch.isQuote()) {
            csvParser.setState(csvParser.getEndQuotes());
        } else if (ch.isEnd()) {
            throw new IllegalCSVFormatException("Illegal csv format: \" should be contained within double quotes");
        } else {
            csvParser.appendCellValue(singleChar);
        }
    }
}
