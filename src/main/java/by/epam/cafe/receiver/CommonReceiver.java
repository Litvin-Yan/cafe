package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;

public interface CommonReceiver extends Receiver {
    /**
     * Change locale.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void changeLocale(RequestContent requestContent) throws ReceiverException;

    /**
     * Open main page.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openMainPage(RequestContent requestContent) throws ReceiverException;

    /**
     * Open admin statistic.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openAdminStatistic(RequestContent requestContent) throws ReceiverException;

    /**
     * Open not found page.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openNotFoundPage(RequestContent requestContent) throws ReceiverException;
}
