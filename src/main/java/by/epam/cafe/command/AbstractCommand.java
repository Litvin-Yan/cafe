package by.epam.cafe.command;

import by.epam.cafe.content.RequestContent;
import by.epam.cafe.receiver.Receiver;
import by.epam.cafe.util.Router;

public abstract class AbstractCommand {
    protected Receiver receiver;

    /**
     * Default constructor.
     *
     * @param receiver receiver
     *
     * @see Receiver
     */
    public AbstractCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute command.
     *
     * @param requestContent request content
     * @return Router
     *
     * @see RequestContent
     * @see Router
     */
    public abstract Router execute(RequestContent requestContent);

    /**
     * Get receiver.
     *
     * @return receiver
     *
     * @see Receiver
     */
    public Receiver getReceiver() {
        return receiver;
    }

}
