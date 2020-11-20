package controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.ResponseResult;
import service.exception.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: homeplus
 * @description: 用来表示异常错误和 session 处理
 * @author: ZEK
 * @create: 2019-03-30 15:21
 **/
public abstract class BaseController {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult<Void> handleException(Exception e) {
        // 判断异常的类型
        if (e instanceof UsernameConflictException) {
            // 用户名冲突，例如注册时，用户名已经被占用
            return new ResponseResult<Void>(401, e);
        } else if (e instanceof UserNotExistException) {
            // 用户不存在
            return new ResponseResult<Void>(404, e);
        } else if (e instanceof PasswordIsErrorException) {
            // 密码错误
            return new ResponseResult<Void>(405, e);
        } else if (e instanceof AppointFailedException) {
            // 预约失败
            return new ResponseResult<Void>(406, e);
        } else if (e instanceof UserNoLoginException) {
            // 用户未登录
            return new ResponseResult<Void>(407, e);
        } else if (e instanceof IOException) {
            return new ResponseResult<Void>(408, e);
        } else if (e instanceof NoMessagePermission) {
            return new ResponseResult<Void>(409, e);
        } else if (e instanceof NoApplyPermission) {
            return new ResponseResult<Void>(410, e);
        } else {
            return new ResponseResult<Void>(600, e);
        }
    }

    /**
     * 从Session中获取当前登录的用户的id
     * @param session
     * @return 当前登录的用户的id
     */
    protected final Integer getUidFromSession(
            HttpSession session) {
        return Integer.valueOf(
                session.getAttribute("uid").toString());
    }
}