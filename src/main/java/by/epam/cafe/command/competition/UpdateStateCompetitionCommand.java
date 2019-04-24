package by.epam.cafe.command.competition;

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

public class UpdateStateCompetitionCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public UpdateStateCompetitionCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), requestContent);

            if (requestContent.getRequestAttributes().get(ACCESS_DENIED) == null) {
                router.setRoutePath(PageType.ADMIN_COMPETITION_ADD.getPage());
                router.setRouteType(RouteType.REDIRECT);

            } else {
                router.setRoutePath(PageType.INDEX.getPage());
                router.setRouteType(RouteType.REDIRECT);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Update state competition receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
            router.setRouteType(RouteType.REDIRECT);
        }

        return router;
    }
}
