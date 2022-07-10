package code;

public class FormatErrorException extends Exception {
    public String message;

    public FormatErrorException(String formatType) {
        message = formatType + " isn't true!!!";
    }
}
