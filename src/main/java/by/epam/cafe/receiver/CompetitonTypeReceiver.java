package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;

public interface CompetitonTypeReceiver extends Receiver {
    /**
     * Open competition type setting.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openCompetitionTypeSetting(RequestContent requestContent) throws ReceiverException;

    /**
     * Update competition type.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateCompetitionType(RequestContent requestContent) throws ReceiverException;

    /**
     * Create competition type.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createCompetitionType(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete competition type.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void deleteCompetitionType(RequestContent requestContent) throws ReceiverException;
}
