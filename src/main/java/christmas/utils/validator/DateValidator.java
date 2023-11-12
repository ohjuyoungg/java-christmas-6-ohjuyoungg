package christmas.utils.validator;

import christmas.utils.constants.ExceptionMessage;
import christmas.view.InputView;

public class DateValidator {

    private static void validateDate(String input) {
        DateValidator.isBlank(input);
        DateValidator.isString(input);
        DateValidator.validateDateRange(Integer.parseInt(input));
    }

    public static int parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionMessage.INVALID_DATE.getExceptionMessage());
            return InputView.userDate();
        }
    }

    public static boolean validateDateErrors(String input) {
        try {
            validateDate(input);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionMessage.INVALID_DATE.getExceptionMessage());
            return false;
        }
    }

    public static void isBlank(String dateValue) {
        if (dateValue == null || dateValue.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE.getExceptionMessage());
        }
    }

    public static void validateDateRange(int dateValue) {
        if (dateValue > 31 || dateValue < 1) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE.getExceptionMessage());
        }
    }

    public static void isString(String dateValue) {
        if (!dateValue.matches("\\d+")) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE.getExceptionMessage());
        }
    }
}