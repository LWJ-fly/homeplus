package service.exception;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-03-30 15:57
 **/
public class UserNoLoginException extends ServiceException {
    public UserNoLoginException() {
        super();
    }

    public UserNoLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserNoLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNoLoginException(String message) {
        super(message);
    }

    public UserNoLoginException(Throwable cause) {
        super(cause);
    }
}
