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

import static by.epam.cafe.constant.GeneralConstant.ACCESS_DENIED;
import static by.epam.cafe.constant.GeneralConstant.IS_BLOCKED;

public class OpenProfileCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenProfileCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), content);

            if (content.getRequestAttributes().containsKey(IS_BLOCKED)) {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.BLOCK.getPage());

            } else if (content.getRequestAttributes().containsKey(ACCESS_DENIED)) {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.ERROR_404.getPage());

            } else {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageType.PROFILE.getPage());
            }


        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open user profile receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
        }

        return router;
    }
}
