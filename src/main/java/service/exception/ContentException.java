package service.exception;

/**
 * @program: homeplus
 * @description: 内容异常
 * @author: ZEK
 * @create: 2019-05-11 14:09
 **/
public class ContentException extends ServiceException {

    public ContentException() {
        super();
    }

    public ContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentException(String message) {
        super(message);
    }

    public ContentException(Throwable cause) {
        super(cause);
    }
}
