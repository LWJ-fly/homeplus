package service;

import pojo.*;
import service.exception.ContentException;
import service.exception.UsernameConflictException;

import java.util.List;

public interface AdminService {

    /**
     * 查询登录表的用户
     * @return
     */
    List<User> selectAllLogin();

    /**
     * 查询消费者表的所有用户
     * @return
     */
    List<Customer> selectAllCustomer();

    /**
     * 插入管理员
     * @param  admin 管理员的全部信息
     * @return 受影响的行数
     */
    Integer insertAdmin(Admin admin) throws UsernameConflictException;

    /**
     * 插入消费者用户
     * @param customer 消费者的全部信息
     * @return 受影响的行数
     */
    Integer insertCustomer(Customer customer) throws UsernameConflictException;

    /**
     * 删除用户
     * @param username 用户的账户名
     * @return 受影响的行数
     */
    Integer updateLoginStatus(String username);

    /**
     * 插入家政人员
     * @param houseKeeper 家政人员的全部信息
     * @return 受影响的行数
     */
    Integer insertHouseKeeper(HouseKeeper houseKeeper);

    List<EchartsData> getOrderData();

    List<EchartsData> getAppoimentData();

    Integer updateUserInfo (int id, String password, int role, int status);

    /**
     * 获取已经上传认证资料的消费者
     * @return
     */
    List<Customer> getAllCustomerCertify();

    /**
     * 获取已经上传认证资料的家政人员
     * @return
     */
    List<HouseKeeper> getAllHousekeeperCertify ();

    /**
     * 获取已经上传认证资料的公司
     * @return
     */
    List<Company> getAllCompanyCertify ();

    /**
     * 通过消费者的编号获取消费者
     * @param id 消费者的编号
     * @return 消费者信息
     */
    Customer getCustomerByID(int id);

    /**
     * 通过家政人员编号获取家政人员
     * @param id 家政人员的编号
     * @return 家政人员信息
     */
    HouseKeeper getHousekeeperByID (int id);

    /**
     * 通过公司编号获取公司信息
     * @param id 公司编号
     * @return 公司信息
     */
    Company getCompanyByID (int id);

    /**
     * 更新消费者的认证状态
     * @param id 用户编号
     * @param status 用户的认证状态
     */
    void updateCustomerStatusByID (int id, int status);

    /**
     * 更新家政人员的认证状态
     * @param id 家政人员编号
     * @param status 家政人员的认证状态
     */
    void updateHousekeeperStatusByID (int id, int status);

    /**
     * 更改公司的认证状态
     * @param id 公司编号
     * @param status 公司的认证状态
     */
    void updateCompanyStatusByID (int id, int status);

    /**
     * 通过手机号获得用户列表
     * @param phone 手机号
     * @return 用户列表
     */
    List<User> getUserByPhone (String phone) throws ContentException;
}
