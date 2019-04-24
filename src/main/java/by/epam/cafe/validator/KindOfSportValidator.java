package by.epam.cafe.validator;

public interface KindOfSportValidator extends Validator {
    /**
     * Is competitors count valid.
     *
     * @param competitorsCount
     * @return
     */
    boolean isCompetitorsCountValid(int competitorsCount);

    /**
     * Is name valid.
     *
     * @param name
     * @return
     */
    boolean isNameValid(String name);
}
