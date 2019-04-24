package by.epam.cafe.receiver;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;

public interface BetReceiver extends Receiver {
    /**
     * Create bet.
     *
     * @param requestContent request content
     * @throws ReceiverException when throw DAOException
     *
     * @see DAOException
     * @see RequestContent
     */
    void createBet(RequestContent requestContent) throws ReceiverException;
}
