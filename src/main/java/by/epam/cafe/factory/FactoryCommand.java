package by.epam.cafe.factory;


import by.epam.cafe.command.AbstractCommand;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.type.CommandType;
import by.epam.cafe.validator.CommonValidator;
import by.epam.cafe.validator.impl.CommonValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.cafe.constant.GeneralConstant.COMMAND;

public class FactoryCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param requestContent requset content
     * @return
     */
    public AbstractCommand initCommand(RequestContent requestContent) {
        CommonValidator commonValidator = new CommonValidatorImpl();
        AbstractCommand command;
        try {
            String[] commandName = requestContent.getRequestParameters().get(COMMAND);
            if (commonValidator.isVarExist(commandName)) {
                CommandType cmdEnum = CommandType.valueOf(commandName[0].toUpperCase());
                command = cmdEnum.getCommand();

            } else {
                LOGGER.log(Level.WARN, "Variable command not found. ");
                command = CommandType.OPEN_PAGE_NOT_FOUND.getCommand();
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Command not exist", e);
            command = CommandType.OPEN_PAGE_NOT_FOUND.getCommand();
        }
        return command;
    }
}
