package CW.Exception;

public class CsvParserException extends RuntimeException {
    public CsvParserException(String errorMsg) {
        super(errorMsg);
    }
}