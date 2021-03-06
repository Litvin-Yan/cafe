package by.epam.cafe.command.comment;

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

public class OpenCommentCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    public OpenCommentCommand(Receiver receiver) {
        super(receiver);
    }
    @Override
    public Router execute(RequestContent content) {
        Router router = new Router();

        try {
            receiver.action(CommandType.takeCommandType(this), content);

            if (!content.getRequestAttributes().containsKey(PAGE_NOT_FOUND)) {
                router.setRoutePath(PageType.COMMENT.getPage());
                router.setRouteType(RouteType.FORWARD);

            } else {
                router.setRoutePath(PageType.ERROR_404.getPage());
                router.setRouteType(RouteType.REDIRECT);
            }

        } catch (ReceiverException e) {
            LOGGER.log(Level.ERROR, "Open menu receiver error", e);
            router.setRoutePath(PageType.SERVER_ERROR.getPage());
            router.setRouteType(RouteType.REDIRECT);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return router;
    }
}
