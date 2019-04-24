package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;

public interface KindOfSportReceiver extends Receiver {
    /**
     * Open kind of sport setting.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openKindOfSportSetting(RequestContent requestContent) throws ReceiverException;

    /**
     * Update kind of sport.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void updateKindOfSport(RequestContent requestContent) throws ReceiverException;

    /**
     * Create kind of sport.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createKindOfSport(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete kind of sport.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void deleteKindOfSport(RequestContent requestContent) throws ReceiverException;
}
