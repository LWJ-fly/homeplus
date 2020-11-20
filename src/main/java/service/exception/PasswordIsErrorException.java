package service.exception;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-03-30 15:57
 **/
public class PasswordIsErrorException extends ServiceException {
    public PasswordIsErrorException() {
        super();
    }

    public PasswordIsErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PasswordIsErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordIsErrorException(String message) {
        super(message);
    }

    public PasswordIsErrorException(Throwable cause) {
        super(cause);
    }
}
