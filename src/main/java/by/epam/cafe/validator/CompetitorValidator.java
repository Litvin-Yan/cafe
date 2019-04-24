package by.epam.cafe.validator;

import by.epam.cafe.entity.CompetitorEntity;

public interface CompetitorValidator extends Validator {
    /**
     * Is places valid.
     *
     * @param competitors
     * @return
     */
    boolean isPlacesValid(CompetitorEntity[] competitors);

    /**
     * Is score valid.
     *
     * @param competitors
     * @return
     */
    boolean isScoreValid(CompetitorEntity[] competitors);
}
