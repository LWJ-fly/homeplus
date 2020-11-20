package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Order;

import java.util.Date;
import java.util.List;

public interface OrderMapper {

    /**
     * 根据消费者的手机号查找所有的订单
     * @param cmID 消费者的编号
     * @return 订单列表
     */
    List<Order> getAllOrder (@Param("cmID") int cmID);

    /**
     * 根据订单 id 获得订单的信息
     * @param id 订单 id
     * @return 订单信息
     */
    Order getOrderByID (@Param("id") int id);

    /**
     * 根据订单 id 删除订单的信息
     * @param id 订单 id
     * @return 受影响的行数
     */
    Integer deleteOrderByID (@Param("id") int id);

    /**
     * 插入订单的信息
     * @param order 订单
     * @return 受影响的行数
     */
    Integer insertOrder (Order order);

    /**
     * 查询对应家政人员的订单
     * @param hkID 家政人员编号
     * @return 家政人员列表
     */
    List<Order> getOrderListByHKID (@Param("hkID") int hkID);

    /**
     * 更新订单的状态
     * @param id 订单的编号
     * @return 受影响的行数
     */
    Integer updateOrderStatusByID (@Param("id") int id, @Param("status") int status, @Param("endTime") Date endTime);

    /**
     * 查询order表，通过家政人员分类，指定类型，获得前三名的家政人员编号
     * @param typeID
     * @return
     */
    List<Integer> getTopOrderByTypeID (@Param("type_id") int typeID);

    /**
     * 查询所有的种类
     * @return 种类的集合
     */
    List<Integer> getAllTypeID ();

    /**
     * 查询所有的用户
     * @return 用户的集合
     */
    List<Integer> getAllCmID ();

    /**
     * 通过用户的编号和类型编号查询类型的订单总数
     * @param cmID 用户的编号
     * @param typeID 类型的编号
     * @return 订单总数
     */
    Integer getOrderNumByTypeIDAndCmID (@Param("cm_id") int cmID, @Param("type_id") int typeID);
}
