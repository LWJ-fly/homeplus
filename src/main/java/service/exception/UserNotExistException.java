package service.exception;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-03-30 15:51
 **/
public class UserNotExistException extends ServiceException {

    public UserNotExistException() {
        super();
    }

    public UserNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistException(String message) {
        super(message);
    }

    public UserNotExistException(Throwable cause) {
        super(cause);
    }
}
