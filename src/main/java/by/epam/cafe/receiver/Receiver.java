package by.epam.cafe.receiver;


import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.type.CommandType;

public interface Receiver {
    /**
     * Action for receiver.
     *
     * @param type command type
     * @param content request content
     * @throws ReceiverException when some error
     */
    default void action(CommandType type, RequestContent content) throws ReceiverException {
        type.doReceiver(content);
    }

}
