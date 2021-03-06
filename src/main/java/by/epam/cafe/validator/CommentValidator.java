package by.epam.cafe.validator;

public interface CommentValidator extends Validator {

    /**
     * Is comment text valid.
     *
     * @param text
     * @return
     */
    boolean isCommentTextValid(String text);
}
