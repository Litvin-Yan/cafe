package by.epam.cafe.command.common;

import by.epam.cafe.command.AbstractCommand;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.Receiver;
import by.epam.cafe.type.CommandType;
import by.epam.cafe.type.PageType;
import by.epam.cafe.type.RouteType;
import by.epam.cafe.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendQuestionEmailCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public SendQuestionEmailCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.HELP.getPage());

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Send question email receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
        }
        return router;
    }
}