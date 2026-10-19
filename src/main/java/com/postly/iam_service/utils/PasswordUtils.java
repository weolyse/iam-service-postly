package com.postly.iam_service.utils;

import com.postly.iam_service.model.constant.ValidationConstants;

public class PasswordUtils {

    public static boolean isNotValidPassword(String password) {
        if (password == null || password.isEmpty() || password.trim().isEmpty()) {
            return true;
        }
        String trim = password.trim();
        if (trim.length() < ValidationConstants.REQUIRED_MIN_PASSWORD_LENGTH) {
            return true;
        }
        int charactersNumber = ValidationConstants.REQUIRED_MIN_CHARACTERS_NUMBER_IN_PASSWORD;
        int lettersUCaseNumber = ValidationConstants.REQUIRED_MIN_LETTERS_NUMBER_EVERY_CASE_IN_PASSWORD;
        int lettersLCaseNumber = ValidationConstants.REQUIRED_MIN_LETTERS_NUMBER_EVERY_CASE_IN_PASSWORD;
        int digitsNumber = ValidationConstants.REQUIRED_MIN_DIGITS_NUMBER_IN_PASSWORD;
        for (int i = 0; i < trim.length(); i++) {
            String currentLetter = String.valueOf(trim.charAt(i));
            if (!ValidationConstants.PASSWORD_ALL_CHARACTERS.contains(currentLetter)) {
                return true;
            }
            charactersNumber -= ValidationConstants.PASSWORD_CHARACTERS.contains(currentLetter) ? 1 : 0;
            lettersUCaseNumber -= ValidationConstants.PASSWORD_LETTERS_UPPER_CASE.contains(currentLetter) ? 1 : 0;
            lettersLCaseNumber -= ValidationConstants.PASSWORD_LETTERS_LOWER_CASE.contains(currentLetter) ? 1 : 0;
            digitsNumber -= ValidationConstants.PASSWORD_DIGITS.contains(currentLetter) ? 1 : 0;
        }
        return ((charactersNumber > 0) || (lettersUCaseNumber > 0) || (lettersLCaseNumber > 0) || (digitsNumber > 0));
    }

}
