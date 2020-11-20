package service;

import com.github.pagehelper.PageInfo;
import pojo.Order;
import service.exception.UserNoLoginException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {

    /**
     * 获得消费者的所有订单
     * @param session
     * @param currentPage 开始页数
     * @return
     */
    PageInfo<Order> getAllOrder (HttpSession session, int currentPage);

    /**
     * 获得订单的详细信息
     * @param id 订单编号
     * @return 订单信息
     */
    Order getOrderByID (int id);

    /**
     * 删除订单的详细信息
     * @param id 订单编号
     * @return 订单信息
     */
    Integer deleteOrderByID (int id);

    /**
     * 通过家政人员的 id 来获得对应的订单列表
     * @param session
     * @return 订单列表
     */
    List<Order> getOrderListByHKID (HttpSession session) throws UserNoLoginException;

    /**
     * 更新订单的状态
     * @param id 订单的编号
     * @param status 订单的状态
     * @return 受影响的行数
     */
    Integer updateOrderStatusByID (int id, int status);

    /**
     * 取消订单
     * @param id 订单编号
     */
    void cancelOrder (int id);
 }
