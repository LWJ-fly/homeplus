package service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.UserService;
import service.exception.PasswordIsErrorException;
import service.exception.UserNotExistException;
import service.exception.UsernameConflictException;

import java.util.List;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-03-30 12:45
 **/

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User insert(User user) throws UsernameConflictException {
        User result = getUserByUsername(user.getUsername());
        if (result == null) {
            userMapper.insert(user);
            System.out.println("注册的账户：" + user.getUsername());
            if (user.getRole() == 0) {
                userMapper.insertCustomer(user);
            } else if (user.getRole() == 1) {
                userMapper.insertHouseKeeper(user);
            }
            return user;
        } else {
            throw new UsernameConflictException("尝试注册的账户(" + user.getUsername() + ")已经被占用！");
        }
    }

    @Override
    public Boolean login(String username, String password) throws UserNotExistException, PasswordIsErrorException {
        Boolean isExist = false;
        User user = getUserByUsername(username);

        if (user == null) {
            throw new UserNotExistException("登录账户（" + username + ")不存在！");
        } else if (!user.getPassword().equals(password)) {
            throw new PasswordIsErrorException("账户名或密码错误！");
        } else if (user != null && user.getPassword().equals(password)) {
            isExist = true;
        }
        return isExist;
    }

    @Override
    public int getLoginRole(String username) {
        return userMapper.selectRole(username);
    }

    private User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
