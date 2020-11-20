package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Customer;
import pojo.Message;

import java.util.List;

public interface PersonMapper {

    /**
     * 根据用户的 phone 查找用户的基本资料
     * @param phone 用户的手机号码
     * @return 用户的基本信息
     */
    Customer selectCustomer (@Param("phone") String phone);

    /**
     * 根据消费者的 id 来获得消费者的信息
     * @param id
     * @return
     */
    Customer selectCustomerByID (@Param("id") int id);

    /**
     * 更新用户的基本资料
     * @param 用户的基本资料
     * @return 受影响的行数
     */
    Integer updateCustomer (@Param("cmNickname") String cmNickname, @Param("cmPhone") String cmPhone
                            , @Param("cmEmail") String cmEmail, @Param("cmHeadPhoto") String cmHeadPhoto
                            , @Param("username") String username);

    /**
     * 更新消费者的密码
     * @param username 消费者的手机号
     * @param newPassword 新密码
     * @return
     */
    Integer updatePassword (@Param("username") String username, @Param("newPassword") String newPassword);

    /**
     * 获得消费者的认证信息
     * @param username 消费者的手机号
     * @return 消费者的信息
     */
     Customer selectCertifyCustomer(String username);

    /**
     * 查询消费者的认证状态
     * @param username 手机号
     * @return 认证状态
     */
     Integer getCertifyStatus (@Param("phone") String username);

    /**
     * 更新消费者的真实姓名、身份证号、身份证手持照片
     * @param name 真实姓名
     * @param cardID 身份证号
     * @param cardImage 身份证手持照片
     * @return
     */
    Integer certifyCustomer (@Param("cm_name") String name, @Param("cm_cardID") String cardID, @Param("cm_cardPhoto") String cardImage, @Param("cm_status") int status, @Param("username") String username);

    /**
     * 获取所有的消息
     * @return 消息的集合
     */
    List<Message> getAllMessage();

    /**
     * 获取单条的消息内容
     * @param id 消息的 id
     * @return 消息的内容
     */
    String getSingleMessage(Integer id);

    /**
     * 删除单条的消息内容
     * @param id 消息的 id
     * @return 受影响的行数
     */
    Integer deleteSingleMessage(Integer id);

    /**
     * 获得所有已经上传认证资料的消费者信息
     * @return
     */
    List<Customer> getAllCustomerByStatus ();

    /**
     * 根据消费者编号查询消费者信息
     * @param id 消费者编号
     * @return 消费者信息
     */
    Customer getCustomerByID (@Param("id") int id);

    /**
     * 更新消费者表的认证状态
     * @param id 消费者编号
     * @return 受影响行数
     */
    Integer updateCustomerStatusByID (@Param("id") int id, @Param("status") int status);
}
