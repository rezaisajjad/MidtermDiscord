package ClientController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static boolean isValidate(String pattern, String text) {
        Pattern _Pattern = Pattern.compile(pattern);
        Matcher matcher = _Pattern.matcher(text);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,100}$";
        return isValidate(pattern, password);
    }

    public static boolean validateEmail(String email) {
        String pattern = "^(.+)@(.+)$";
        return isValidate(pattern,email);
    }
    public static boolean validatePhoneNumber(String phoneNumber) {
        String pattern = "^\\d{11}$";
        return isValidate(pattern,phoneNumber);
    }
    public static boolean validateUserName(String userName) {
        String pattern = "^[A-Za-z][A-Za-z0-9]{5,100}$";
        Pattern _Pattern = Pattern.compile(pattern);
        return isValidate(pattern, userName);
    }
}
