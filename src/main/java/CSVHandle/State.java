package CSVHandle;

/**
 * Created by Simba on 2017/2/7.
 */
public interface State {
    public void parse(Char ch) throws IllegalCSVFormatException;
}
