package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Admin;
import pojo.Customer;
import pojo.EchartsData;
import pojo.User;

import java.util.List;

public interface AdminMapper {

    /**
     * 查询登录表中的所有用户
     * @return 用户的集合
     */
    List<User> selectAllLogin();

    /**
     * 查询消费者表中的所有消费者
     * @return 消费者的集合
     */
    List<Customer> selectAllCustomer();

    /**
     * 插入消费者
     * @param customer 消费者的全部信息
     * @return 受影响的行数
     */
    Integer insertCustomer(Customer customer);

//    Integer insertHouseKeeper();

    /**
     * 插入管理员
     * @param admin 管理员的全部信息
     * @return 受影响的行数
     */
    Integer insertAdmin(Admin admin);

    /**
     * 更新登录用户的状态，将 1 变为 0
     * 即表示该用户无效，相当于删除了该用户
     * @param username 登录用户的账户名
     * @return 受影响的行数
     */
    Integer updateStatusLogin(String username);

    List<EchartsData> getOrderData();

    List<EchartsData> getAppoimentData();

    /**
     * 更新用户的信息
     * @param id 用户的编号
     * @param password 用户的密码
     * @param role 用户角色
     * @return 受影响的行数
     */
    Integer updateUserInfo (@Param("id") int id, @Param("password") String password, @Param("role") int role, @Param("status") int status);

    /**
     * 更新管理员的密码
     * @param phone 管理员的手机号
     * @param password 管理员的密码
     * @return 受影响行数
     */
    Integer updatePassword (@Param("phone") String phone, @Param("password") String password);

    /**
     * 手机号查询用户
     * @param phone 手机号
     * @return 用户列表
     */
    List<User> getUserByPhone (@Param("phone") String phone);
}
