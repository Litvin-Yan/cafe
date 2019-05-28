package by.epam.cafe.type;

import by.epam.cafe.command.AbstractCommand;
import by.epam.cafe.command.comment.ChangeLockCommentCommand;
import by.epam.cafe.command.comment.CreateCommentCommand;
import by.epam.cafe.command.common.*;
import by.epam.cafe.command.order.OpenBasketCommand;
import by.epam.cafe.command.orderdata.AddProductCommand;
import by.epam.cafe.command.product.OpenMenuCommand;
import by.epam.cafe.command.orderdata.RemoveProductCommand;
import by.epam.cafe.command.user.*;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.DAOException;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.impl.*;

import java.util.*;

public enum CommandType {

    ADD_PRODUCT(new AddProductCommand(new OrderDataReceiverImpl())){
        public void doReceiver(RequestContent content) throws ReceiverException, DAOException {
            ((OrderDataReceiverImpl) getCommand().getReceiver()).addProduct(content);
        }
    },

    REMOVE_PRODUCT(new RemoveProductCommand(new OrderDataReceiverImpl())){
        public void doReceiver(RequestContent content) throws ReceiverException, DAOException {
            ((OrderDataReceiverImpl) getCommand().getReceiver()).removeProduct(content);
        }
    },

    SIGN_IN(new SignInCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).signIn(content);
        }
    },

    SIGN_UP(new SignUpCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).signUp(content);
        }
    },

    SIGN_OUT(new SignOutCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) {
            ((UserReceiverImpl) getCommand().getReceiver()).signOut(content);
        }
    },

    OPEN_PROFILE(new OpenProfileCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).openProfile(content);
        }
    },

    WITHDRAW_MONEY(new WithdrawMoneyCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).withdrawMoney(content);
        }
    },

    ADD_MONEY(new AddMoneyCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).addMoney(content);
        }
    },

    CHANGE_AVATAR(new UpdateAvatarCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changeAvatar(content);
        }
    },

    CHANGE_PASSWORD(new UpdatePasswordCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changePassword(content);
        }
    },

    OPEN_PAGE_NOT_FOUND(new OpenPageNotFoundCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openNotFoundPage(content);
        }
    },

    CHANGE_LOCALE(new ChangeLocaleCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).changeLocale(content);
        }
    },

    OPEN_MAIN(new OpenMainCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openMainPage(content);
        }
    },
    CHANGE_LOCK_COMMENT(new ChangeLockCommentCommand(new CommentReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommentReceiverImpl) getCommand().getReceiver()).changeLockComment(content);
        }
    },

    CREATE_COMMENT(new CreateCommentCommand(new CommentReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommentReceiverImpl) getCommand().getReceiver()).createComment(content);
        }
    },

    OPEN_USER_SETTINGS(new OpenUserSettingsCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).openUserSettings(content);
        }
    },

    CHANGE_USER_ROLE(new UpdateUserRoleCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changeRole(content);
        }
    },

    OPEN_ADMIN_STATISTIC(new OpenAdminStatisticCommand(new CommonReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((CommonReceiverImpl) getCommand().getReceiver()).openAdminStatistic(content);
        }
    },

    CHANGE_USER_LOCK(new UpdateUserLockCommand(new UserReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((UserReceiverImpl) getCommand().getReceiver()).changeLock(content);
        }
    },

    OPEN_MENU(new OpenMenuCommand(new ProductReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((ProductReceiverImpl) getCommand().getReceiver()).openMenuPage(content);
        }
    },

    OPEN_BASKET(new OpenBasketCommand(new OrderReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((OrderReceiverImpl) getCommand().getReceiver()).openBasketPage(content);
        }
    },

    CREATE_ORDER(new OpenBasketCommand(new OrderReceiverImpl())) {
        public void doReceiver(RequestContent content) throws ReceiverException {
            ((OrderReceiverImpl) getCommand().getReceiver()).createOrder(content);
        }
    };


    private AbstractCommand command;

    CommandType(AbstractCommand command) {
        this.command = command;
    }

    public static CommandType takeCommandType(AbstractCommand command) {
        ArrayList<CommandType> result = new ArrayList<>();
        List<CommandType> types = Arrays.asList(CommandType.values());
        types.stream().filter(t -> t.getCommand() == command).forEach(result::add);
        return result.get(0);
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public abstract void doReceiver(RequestContent content) throws ReceiverException, DAOException;
}
