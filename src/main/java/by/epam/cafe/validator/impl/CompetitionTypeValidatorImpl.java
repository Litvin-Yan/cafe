package by.epam.cafe.validator.impl;

import by.epam.cafe.validator.CompetitionTypeValidator;

public class CompetitionTypeValidatorImpl implements CompetitionTypeValidator {
    private static final int MAX_NAME_LENGTH = 200;

    @Override
    public boolean isNameValid(String name) {
        boolean isValid = true;

        if (name == null || name.trim().isEmpty() || name.trim().length() > MAX_NAME_LENGTH) {
            isValid = false;
        }

        return isValid;
    }
}
