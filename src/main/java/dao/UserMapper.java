package dao;

import org.apache.ibatis.annotations.Param;
import pojo.User;


public interface UserMapper {

    /**
     * 在 login 表中插入用户
     * @param user 用户信息
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 在 login 表中查找用户的角色类型
     * @param username 用户的手机号
     * @return 用户的角色类型， 0 代表 消费者， 1 代表家政人员， -1 代表管理员
     */
    Integer selectRole (@Param("username") String username);

    /**
     * 在 Customer 表中插入用户
     * @param user 用户的账户名和密码
     * @return 受影响的行数
     */
    Integer insertCustomer(User user);

    /**
     * 根据消费者的手机号查找消费者的id
     * @param phone 手机号
     * @return 消费者的 id
     */
    Integer selectCmIDByPhone (@Param("phone") String phone);


    /**
     * 在 houseKeeper 表中插入用户
     * @param user 用户的账户名和密码
     * @return 受影响的行数
     */
    Integer insertHouseKeeper(User user);

    /**
     * 根据家政人员的 id 来查找家政人员的姓名
     * @param hkID 家政人员的编号
     * @return 家政人员的姓名
     */
    String selectHKNameByHkID (@Param("hkID") Integer hkID);

    /**
     * 根据家政人员的 id 来查找家政人员的昵称
     * @param hkID 家政人员的编号
     * @return 家政人员的昵称
     */
    String selectHKNicknameByHkID (@Param("hkID") Integer hkID);

    /**
     * 根据家政人员的手机号来查找家政人员的编号
     * @param phone 家政人员的手机号
     * @return 家政人员的编号
     */
    Integer selectHKIDByPhone (@Param("hkPhone") String phone);

    /**
     * 通过用户账户在 login 表中查找用户
     * @param username 用户账户名称
     * @return 用户
     */
    User getUserByUsername(String username);

    /**
     * 根据用户手机号来获得用户的密码
     * @param username 用户手机号
     * @return 用户的密码
     */
    String getPasswordByUsername (String username);

    /**
     * 更新用户手机号来更新密码
     * @param username 用户手机号
     * @param newPassword 新密码
     * @return 受影响的行数
     */
    Integer updatePassword (@Param("username") String username, @Param("newPassword") String newPassword);

    /**
     * 获取用户的手机号
     * @param id 用户编号
     * @return 用户的手机号
     */
    String selectPhoneByID (@Param("id") int id);
}
