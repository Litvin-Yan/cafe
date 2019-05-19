package by.epam.cafe.command.user;

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

import java.util.Set;

import static by.epam.cafe.constant.GeneralConstant.IS_BLOCKED;
import static by.epam.cafe.constant.GeneralConstant.WRONG_DATA;

public class SignInCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public SignInCommand(Receiver receiver) {
        super(receiver);
    }

    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            Set<String> keys = requestContent.getRequestAttributes().keySet();

            if (keys.contains(WRONG_DATA)) {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageType.SIGN_IN.getPage());

            } else if (keys.contains(IS_BLOCKED)) {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.BLOCK.getPage());

            } else {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.INDEX.getPage());
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Handle receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
        }

        return router;
    }
}
