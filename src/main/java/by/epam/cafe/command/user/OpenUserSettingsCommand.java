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

import static by.epam.cafe.constant.GeneralConstant.PAGE_NOT_FOUND;

public class OpenUserSettingsCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenUserSettingsCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            if (!requestContent.getRequestAttributes().containsKey(PAGE_NOT_FOUND)) {
                router.setRouteType(RouteType.FORWARD);
                router.setRoutePath(PageType.ADMIN_USER.getPage());

            } else {
                router.setRouteType(RouteType.REDIRECT);
                router.setRoutePath(PageType.ERROR_404.getPage());
            }


        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open user settings receiver error", e);
            router.setRouteType(RouteType.REDIRECT);
            router.setRoutePath(PageType.SERVER_ERROR.getPage());
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return router;
    }
}
