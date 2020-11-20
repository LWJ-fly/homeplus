package service.impl;

import dao.PersonMapper;
import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.Customer;
import pojo.Message;
import service.PersonService;
import service.exception.PasswordIsErrorException;
import service.exception.UserNoLoginException;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-04-15 12:24
 **/

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Customer selectCustomer(HttpSession session) throws UserNoLoginException {
        String phone = (String)session.getAttribute("username");
        if (StringUtils.isEmpty(phone)) {
            throw new UserNoLoginException("用户未登录！");
        } else {
            return personMapper.selectCustomer(phone);
        }
    }

    @Override
    public Integer updateCustomer(HttpSession session, Customer cm) {
        Integer result = 0;
        if (session == null) {
            throw new UserNoLoginException("用户未登录...");
        } else {
            String username = session.getAttribute("username").toString();
            result = personMapper.updateCustomer(cm.getCmNickname(), cm.getCmPhone(), cm.getCmEmail(), cm.getCmHeadPhoto(), username);
        }
        return result;
    }

    @Override
    public Integer updatePassword(HttpSession session, String oldPassword, String newPassword) throws UserNoLoginException, PasswordIsErrorException {
        Integer result = 0;
        String username = new String();
        if (session == null) {
            throw new UserNoLoginException("用户未登录");
        } else {
            username = session.getAttribute("username").toString();
            String tempPwd = userMapper.getPasswordByUsername(username);
            if (!tempPwd.equals(oldPassword)) {
                throw new PasswordIsErrorException("原始密码填写错误！");
            } else {
                userMapper.updatePassword(username, newPassword);
                result = personMapper.updatePassword(username, newPassword);
            }
        }
        return result;
    }

    @Override
    public Customer selectCertifyCustomer(HttpSession session) {
        Customer customer = new Customer();
        if (session == null) {
            throw new UserNoLoginException("用户未登录！");
        } else {
            String username = session.getAttribute("username").toString();
            customer = personMapper.selectCertifyCustomer(username);
        }
        return customer;
    }

    @Override
    public Integer certifyCustomer(HttpSession session, Customer customer) throws UserNoLoginException {
        int result = 0;
        String username = new String();
        if (session == null) {
            throw new UserNoLoginException("用户未登录");
        } else {
            username = session.getAttribute("username").toString();
            result = personMapper.certifyCustomer(customer.getCmName(), customer.getCmCardID(), customer.getCmCardPhoto(), customer.getCmStatus(), username);
        }
        return result;
    }

    @Override
    public Integer getCertifyStatus(HttpSession session) {
        int result = 0;
        String username = (String)session.getAttribute("username");
        if (StringUtils.isEmpty(username)) {
            throw new UserNoLoginException("用户未登录");
        } else {
            result = personMapper.getCertifyStatus(username);
        }
        return result;
    }

    @Override
    public List<Message> getAllMessage() {
        return personMapper.getAllMessage();
    }

    @Override
    public String getSingleMessage(Integer id) {
        return personMapper.getSingleMessage(id);
    }

    @Override
    public Integer deleteSingleMessage(Integer id) {
        return personMapper.deleteSingleMessage(id);
    }
}
