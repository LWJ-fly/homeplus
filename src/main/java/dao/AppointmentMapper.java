package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Appointment;

import java.util.List;

/**
 * @program: homeplus
 * @description: 预约表的mapper类
 * @author: ZEK
 * @create: 2019-04-08 14:02
 **/
public interface AppointmentMapper {

    /**
     * 向预约表中插入预约数据
     * @param appoint
     * @return
     */
    Integer insertAppointment(Appointment appoint);

    /**
     * 通过手机号码来获得消费者的 id
     * @param username
     * @return
     */
    Integer getCustomerIDByUsername(@Param("username") String username);

    /**
     * 通过订单的状态来查找所有的有效订单
     * @param status 订单状态
     * @return 所有的有效订单
     */
    List<Appointment> getStatusApp (@Param("status") int status);

    /**
     * 获取所有的订单
     * @return 所有的订单
     */
    List<Appointment> getAllApp();

    /**
     * 通过订单编号获得订单申请的列表
     * @param id 订单编号
     * @return 申请列表
     */
    String getApplyList (@Param("id") int id);

    /**
     * 更新家政人员的申请列表
     * @param apply_list 家政人员的编号
     * @return 受影响行数
     */
    Integer updateApplyList (@Param("applyList") String apply_list, @Param("id") int id);

    /**
     * 通过预约的编号获得一条预约的信息
     * @param id 预约的编号
     * @return 一条预约的信息
     */
    Appointment selectAppointByID (@Param("id") int id);

    /**
     * 更新预约的状态
     * @param id
     * @param status 预约的状态
     * @return
     */
    Integer updateAppointStatus (@Param("id") int id, @Param("status") int status);

    /**
     * 删除指定预约编号的预约
     * @param id 预约编号
     * @return 受影响的行数
     */
    Integer deleteAppointByID (@Param("id") int id);

    /**
     * 插入最后的家政人员的编号
     * @param hkID 家政人员的编号
     * @return 受影响的行数
     */
    Integer insertHKID (@Param("id") int id,@Param("hkID") int hkID);

    /**
     * 通过cmID查询预约
     * @param cmID
     * @return
     */
    List<Appointment> selectAppListByCmID (@Param("cm_id") int cmID);
}
