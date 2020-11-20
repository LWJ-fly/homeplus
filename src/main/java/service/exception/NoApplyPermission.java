package service.exception;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-05-13 09:36
 **/
public class NoApplyPermission extends ServiceException {

    public NoApplyPermission() {
        super();
    }

    public NoApplyPermission(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoApplyPermission(String message, Throwable cause) {
        super(message, cause);
    }

    public NoApplyPermission(String message) {
        super(message);
    }

    public NoApplyPermission(Throwable cause) {
        super(cause);
    }
}
