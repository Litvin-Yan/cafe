package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;

public interface NewsReceiver extends Receiver {

    /**
     * Open concrete news page.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openConcreteNewsPage(RequestContent requestContent) throws ReceiverException;

    /**
     * Open all news.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void openAllNewsPage(RequestContent requestContent) throws ReceiverException;

    /**
     * Create news.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createNews(RequestContent requestContent) throws ReceiverException;

    /**
     * Delete news.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void deleteNews(RequestContent requestContent) throws ReceiverException;
}
