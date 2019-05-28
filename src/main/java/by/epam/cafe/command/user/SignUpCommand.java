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

import java.util.Set;

import static by.epam.cafe.constant.RequestNameConstant.*;

public class SignUpCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public SignUpCommand(Receiver receiver) {
        super(receiver);
    }

    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);
            Set<String> keys = requestContent.getRequestAttributes().keySet();

            if (keys.contains(WRONG_EMAIL) || keys.contains(WRONG_NAME) ||
                    keys.contains(WRONG_PASSWORD) || keys.contains(WRONG_REPEAT_PASSWORD) ||
                    keys.contains(EMAIL_EXISTS)) {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageType.SIGN_UP.getPage());

            } else {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.INDEX.getPage());
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Sign up receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.SERVER_ERROR.getPage());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return router;
    }
}
