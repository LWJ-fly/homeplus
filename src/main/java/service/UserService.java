package service;

import pojo.User;
import service.exception.PasswordIsErrorException;
import service.exception.UserNotExistException;
import service.exception.UsernameConflictException;

public interface UserService {

    /**
     * 用户注册
     * @param user 用户信息
     * @return 用户的相关信息
     * @throws UsernameConflictException 账户名冲突异常
     */
    User insert(User user) throws UsernameConflictException;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录是否成功
     * @throws UserNotExistException 用户不存在的异常
     * @throws PasswordIsErrorException 密码错误的异常
     */
    Boolean login(String username, String password) throws UserNotExistException, PasswordIsErrorException;

    /**
     * 获取登录成功的角色
     * @param username
     * @return
     */
    int getLoginRole (String username);

}
