package CSVHandle;

/**
 * Created by Simba on 2017/2/7.
 */
public class IllegalCSVFormatException extends Exception {
    public IllegalCSVFormatException(String message) {
        super(message);
    }

    public IllegalCSVFormatException() {
        super();
    }

    public IllegalCSVFormatException(Throwable cause) {
        super(cause);
    }


    public IllegalCSVFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
