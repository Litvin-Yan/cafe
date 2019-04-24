package by.epam.cafe.command.competition;

import by.epam.cafe.command.AbstractCommand;
import by.epam.cafe.constant.GeneralConstant;
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

public class OpenPastCompetitionsCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OpenPastCompetitionsCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), content);

            if (!content.getRequestAttributes().containsKey(GeneralConstant.PAGE_NOT_FOUND)) {
                router.setRoutePath(PageType.ALL_PAST_COMPETITIONS.getPage());
                router.setRouteType(RouteType.FORWARD);

            } else {
                router.setRoutePath(PageType.ERROR_404.getPage());
                router.setRouteType(RouteType.REDIRECT);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open past competitions receiver error", e);
            router.setRoutePath(PageType.ERROR_SERVER.getPage());
            router.setRouteType(RouteType.REDIRECT);
        }
        return router;
    }
}
