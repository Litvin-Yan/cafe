package by.epam.cafe.validator.impl;

import by.epam.cafe.validator.ProductValidator;

public class ProductValidatorImpl implements ProductValidator {

    public static final int MAX_TITLE_LENGTH = 45;
    public static final int MAX_TEXT_LENGTH = 16_770_000;

    @Override
    public boolean isTitleValid(String title) {
        boolean isValid = true;

        if (title == null || title.trim().isEmpty() || title.trim().length() > MAX_TITLE_LENGTH) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isTextValid(String text) {
        boolean isValid = true;

        if (text == null || text.trim().isEmpty() || text.trim().length() > MAX_TEXT_LENGTH) {
            isValid = false;
        }

        return isValid;
    }

}
