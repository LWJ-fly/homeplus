package service.impl;

import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.*;
import service.AdminService;
import service.exception.ContentException;
import service.exception.UsernameConflictException;

import java.util.List;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-04-09 15:14
 **/

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private HKPersonMapper hkPersonMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<User> selectAllLogin() {
        return adminMapper.selectAllLogin();
    }

    @Override
    public List<Customer> selectAllCustomer() {
        return adminMapper.selectAllCustomer();
    }

    @Override
    public Integer insertAdmin(Admin admin) throws UsernameConflictException {
        Integer result = 0;
        String phone = admin.getAdPhone();
        if (userMapper.getUserByUsername(phone) != null) {
            throw new UsernameConflictException("用户已经存在！");
        } else {
            String password = admin.getAdPassword();
            int role = -1;
            int status = 1;
            User user = new User();
            user.setUsername(phone);
            user.setPassword(password);
            user.setRole(role);
            user.setStatus(status);
            result = adminMapper.insertAdmin(admin);
            userMapper.insert(user);
        }
        return result;
    }

    @Override
    public Integer insertCustomer(Customer customer) throws UsernameConflictException {
        Integer result = 0;
        String phone = customer.getCmPhone();
        if (userMapper.getUserByUsername(phone) != null) {
            throw new UsernameConflictException("用户已经存在！");
        } else {
            String password = customer.getCmPassword();
            int role = 1;
            int status = 1;
            User user = new User();
            user.setUsername(phone);
            user.setPassword(password);
            user.setRole(role);
            user.setStatus(status);
            result = adminMapper.insertCustomer(customer);
            userMapper.insert(user);
        }
        return result;
    }

    @Override
    public Integer updateLoginStatus(String username) {
        Integer result = 0;
        result = adminMapper.updateStatusLogin(username);
        return result;
    }

    @Override
    public Integer insertHouseKeeper(HouseKeeper houseKeeper) {

        return null;
    }

    @Override
    public List<EchartsData> getOrderData() {
        return adminMapper.getOrderData();
    }

    @Override
    public List<EchartsData> getAppoimentData() {
        return adminMapper.getAppoimentData();
    }

    @Override
    public Integer updateUserInfo(int id, String password, int role, int status) {
        int result = adminMapper.updateUserInfo(id, password, role, status);
        String phone = userMapper.selectPhoneByID(id);
        if (role == 0) {
            personMapper.updatePassword(phone, password);
        } else if (role == 1) {
            hkPersonMapper.updatePasswordByPhone(phone, password);
        } else if (role == -1) {
            adminMapper.updatePassword(phone, password);
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomerCertify() {
        return personMapper.getAllCustomerByStatus();
    }

    @Override
    public List<HouseKeeper> getAllHousekeeperCertify() {
        return hkPersonMapper.getAllHousekeeperByStatus();
    }

    @Override
    public List<Company> getAllCompanyCertify() {
        return companyMapper.getCompanyListByStatus();
    }

    @Override
    public Customer getCustomerByID(int id) {
        return personMapper.getCustomerByID(id);
    }

    @Override
    public HouseKeeper getHousekeeperByID(int id) {
        return hkPersonMapper.getHousekeeperByID(id);
    }

    @Override
    public Company getCompanyByID(int id) {
        return companyMapper.getCompanyByID(id);
    }

    @Override
    public void updateCustomerStatusByID(int id, int status) {
        personMapper.updateCustomerStatusByID(id, status);
    }

    @Override
    public void updateHousekeeperStatusByID(int id, int status) {
        hkPersonMapper.updateHousekeeperStatusByID(id, status);
    }

    @Override
    public void updateCompanyStatusByID(int id, int status) {
        companyMapper.updateCompanyStatusByID(id, status);
    }

    @Override
    public List<User> getUserByPhone(String phone) throws ContentException {

        if (StringUtils.isEmpty(phone)) {
            throw new ContentException("输入的内容为空");
        } else if (adminMapper.getUserByPhone(phone).size() <= 0) {
            throw new ContentException("没有相关内容");
        } else {
            return adminMapper.getUserByPhone(phone);
        }
    }
}
