package by.epam.cafe.command.bet;

import by.epam.cafe.command.AbstractCommand;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.Receiver;
import by.epam.cafe.type.CommandType;
import by.epam.cafe.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateBetCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public CreateBetCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle create bet receiver error", e);
        }

        return null;
    }
}
