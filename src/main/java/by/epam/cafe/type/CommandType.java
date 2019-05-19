package by.epam.cafe.type;

import by.epam.cafe.command.AbstractCommand;
import by.epam.cafe.command.comment.ChangeLockCommentCommand;
import by.epam.cafe.command.comment.CreateCommentCommand;
import by.epam.cafe.command.common.*;
import by.epam.cafe.command.user.*;
import by.epam.cafe.content.RequestContent;
import by.epam.cafe.exception.ReceiverException;
import by.epam.cafe.receiver.Receiver;
import by.epam.cafe.receiver.UserReceiver;
import by.epam.cafe.receiver.impl.*;

import java.util.*;

public enum CommandType {


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

    public abstract void doReceiver(RequestContent content) throws ReceiverException;
}
