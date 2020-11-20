package service.impl;

import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.Appointment;
import pojo.Order;
import pojo.User;
import service.AppointmentService;
import service.IndexService;
import service.exception.AppointFailedException;
import service.exception.NoApplyPermission;
import service.exception.UserNoLoginException;
import utils.JsonUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-04-08 14:40
 **/

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper appMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private HKPersonMapper hkPersonMapper;

    @Override
    public Integer insertAppointment(Appointment appointment, String username) throws AppointFailedException, UserNoLoginException {
        Integer cmID = appMapper.getCustomerIDByUsername(username);
        if (cmID == null) {
            throw new UserNoLoginException("用户未登录！");
        }
        appointment.setCmID(cmID);
        Integer result = appMapper.insertAppointment(appointment);
        if (result > 0) {
            return result;
        } else {
            throw new AppointFailedException("预约失败！");
        }
    }

    @Override
    public List<Appointment> getStatusApp(HttpSession session, int status) throws UserNoLoginException {
        int hkID = 0;
        if (session == null) {
            throw new UserNoLoginException("用户未登录");
        } else {
            String username = session.getAttribute("username").toString();
            hkID = userMapper.selectHKIDByPhone(username);
        }
        List<Appointment> list = appMapper.getStatusApp(status);
        for (Appointment app : list) {
            List<Integer> applyList = JsonUtil.json2List(app.getApplyJson(), Integer.class);
            app.setApplyList(applyList);
            app.setHkID(hkID);
        }
        return list;
    }

    @Override
    public List<Appointment> getAllAppCustomer(HttpSession session) {
        int cmID = 0;
        String username = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(username)) {
            throw new UserNoLoginException("用户未登录");
        } else {
            cmID = userMapper.selectCmIDByPhone(username);
        }
        List<Appointment> list = appMapper.selectAppListByCmID(cmID);
        for (Appointment app : list) {
            List<Integer> applyList = JsonUtil.json2List(app.getApplyJson(), Integer.class);
            app.setApplyList(applyList);
        }
        return list;
    }

    /**
     * 家政人员获得预约列表
     * @param session
     * @return
     */
    @Override
    public List<Appointment> getAllApp(HttpSession session) {
        int hkID = 0;
        String username = (String)session.getAttribute("username");
        if (StringUtils.isEmpty(username)) {
            throw new UserNoLoginException("用户未登录");
        } else {
            hkID = userMapper.selectHKIDByPhone(username);
        }
        List<Appointment> list = appMapper.getAllApp();
        for (Appointment app : list) {
            List<Integer> applyList = JsonUtil.json2List(app.getApplyJson(), Integer.class);
            app.setApplyList(applyList);
            app.setHkID(hkID);
        }
        return list;
    }

    @Override
    public Integer insertApplyList(HttpSession session, int id) throws UserNoLoginException {
        // 通过订单的 id 来获得订单的申请列表
        String applyList = appMapper.getApplyList(id);
        System.out.println(applyList);
        // 通过 工具类将 json 转换为集合
        List<Integer> list = new ArrayList<>();
        list =  JsonUtil.json2List(applyList, Integer.class);
        // 在集合中添加家政人员的编号
        if (session == null) {
            throw new UserNoLoginException("用户未登录");
        } else {
            String username = session.getAttribute("username").toString();
            // 通过手机号判断家政人员是否已经认证，若未认证，则填写认证信息
            if (hkPersonMapper.selectHKStatusByPhone(username) == 0) {
                throw new NoApplyPermission("请填写认证信息");
            } else if (hkPersonMapper.selectHKStatusByPhone(username) == 2) {
                throw new NoApplyPermission("请等待认证结果");
            } else {
                int hkID = userMapper.selectHKIDByPhone(username);
                System.out.println(hkID);
                list.add(new Integer(hkID));
            }
        }
        // 然后将集合转换为 json 格式
        applyList = JsonUtil.toJSONString(list);
        // 最后将 json 插入到数据库中
        return appMapper.updateApplyList(applyList, id);
    }

    @Override
    public Appointment selectAppointByID(int id) throws UserNoLoginException {
        // 根据预约的 id 获得一条预约的信息
        Appointment app = appMapper.selectAppointByID(id);
        List<String> applyNicknameList = new ArrayList<>();
        // 获得一条预约的申请列表
        List<Integer> applyList = JsonUtil.json2List(app.getApplyJson(), Integer.class);
        app.setApplyList(applyList);
        // 根据预约的申请列表中的家政人员的编号，来设置申请列表的家政人员的昵称
        for (int i = 0; i < applyList.size(); i ++) {
            String applyNickname = userMapper.selectHKNicknameByHkID(applyList.get(i));
            applyNicknameList.add(applyNickname);
        }
        // 将申请列表的家政人员的昵称放到实体类中
        app.setApplyNameList(applyNicknameList);
        return app;
    }

    @Override
    public Integer updateAppointStatus(int status, int id) {
        return appMapper.updateAppointStatus(id, status);
    }

    @Override
    public Integer deleteAppointByID(int id) {
        return appMapper.deleteAppointByID(id);
    }

    @Override
    public Integer insertConfirmHKID(String username, int id, int hkID) {
        Integer result = 0;
        Order order = new Order();
        // 获得消费者的编号
        Integer cmID = appMapper.getCustomerIDByUsername(username);
        // 获得预约的内容
        Appointment appoint = appMapper.selectAppointByID(id);
        // 创建订单开始时间
        Date now = new Date();
        // 给订单指定信息
        order.setOrderBeginTime(now);
        order.setHkID(hkID);
        order.setOrderStatus(-1);
        order.setOrderPhone(appoint.getAppPhone());
        order.setOrderAddress(appoint.getAppAddress());
        order.setCmID(cmID);
        order.setTypeID(typeMapper.getTypeIDByName(appoint.getAppType()));
        // 在预约表中插入家政人员的编号
        result = appMapper.insertHKID(id, hkID);
        // 更新预约的状态
        appMapper.updateAppointStatus(id, 0);
        // 在订单表中插入订单
        orderMapper.insertOrder(order);
        return result;
    }
}
