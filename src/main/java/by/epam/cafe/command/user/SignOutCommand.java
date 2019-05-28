package by.epam.cafe.command.user;

import by.epam.cafe.command.AbstractCommand;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.Receiver;
import by.epam.cafe.type.CommandType;
import by.epam.cafe.type.PageType;
import by.epam.cafe.type.RouteType;
import by.epam.cafe.util.Router;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignOutCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public SignOutCommand(Receiver receiver) {
        super(receiver);
    }

    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            router.setRoutePath(PageType.INDEX.getPage());
            router.setRouteType(RouteType.REDIRECT);

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle receiver error", e);
            router.setRoutePath(PageType.SERVER_ERROR.getPage());
            router.setRouteType(RouteType.REDIRECT);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return router;
    }
}
