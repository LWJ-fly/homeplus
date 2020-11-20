package service;

import pojo.Customer;
import pojo.Message;
import service.exception.PasswordIsErrorException;
import service.exception.UserNoLoginException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface PersonService {

    /**
     * 得到消费者的基本资料
     * @param session 通过session 获得手机号
     * @return 消费者
     */
    Customer selectCustomer(HttpSession session) throws UserNoLoginException;

    /**
     * 更新消费者的基本资料
     * @param session
     * @param customer 前台得到的基本信息
     * @return 受影响的行数
     */
    Integer updateCustomer (HttpSession session, Customer customer);

    /**
     * 更新消费者的密码
     * @param session
     * @param oldPassword 消费者的老密码
     * @param newPassword 消费者的新密码
     * @return
     */
    Integer updatePassword (HttpSession session, String oldPassword, String newPassword) throws UserNoLoginException, PasswordIsErrorException;

    /**
     * 获得登录用户的认证信息
     * @param session
     * @return 认证信息
     */
    Customer selectCertifyCustomer (HttpSession session) throws UserNoLoginException;

    /**
     * 消费者的认证
     * @param session
     * @param customer 消费者的信息
     * @return
     */
    Integer certifyCustomer (HttpSession session, Customer customer) throws UserNoLoginException;

    /**
     * 获得消费者的认证状态
     * @param session
     * @return 认证状态
     */
    Integer getCertifyStatus (HttpSession session);

    /**
     * 获取所有的消息
     * @return 消息集合
     */
    List<Message> getAllMessage();

    /**
     * 获取单条消息内容
     * @param id 消息的 id
     * @return 消息的内容
     */
    String getSingleMessage(Integer id);

    /**
     * 删除单条消息内容
     * @param id 消息的 id
     * @return 受影响的行数
     */
    Integer deleteSingleMessage(Integer id);
}
