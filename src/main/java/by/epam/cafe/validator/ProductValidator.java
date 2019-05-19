package by.epam.cafe.validator;

public interface ProductValidator extends Validator {
    /**
     * Is title valid.
     *
     * @param title
     * @return
     */
    boolean isTitleValid(String title);

    /**
     * Is text valid.
     *
     * @param text
     * @return
     */
    boolean isTextValid(String text);

}
