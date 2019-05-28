package by.epam.cafe.command.common;

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

import static by.epam.cafe.constant.GeneralConstant.ACCESS_DENIED;

public class OpenAdminStatisticCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenAdminStatisticCommand(Receiver receiver) {
        super(receiver);
    }

    //TODO: catch dao exception
    @Override
    public Router execute(RequestContent requestContent){
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            if (requestContent.getRequestAttributes().containsKey(ACCESS_DENIED)) {
                router.setRoutePath(PageType.ERROR_404.getPage());
                router.setRouteType(RouteType.REDIRECT);

            } else {
                router.setRoutePath(PageType.ADMIN_MAIN.getPage());
                router.setRouteType(RouteType.FORWARD);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open admin statistic receiver error", e);
            router.setRoutePath(PageType.SERVER_ERROR.getPage());
            router.setRouteType(RouteType.REDIRECT);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return router;
    }
}
