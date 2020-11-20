package service.impl;

import dao.CommentMapper;
import dao.HKPersonMapper;
import dao.OrderMapper;
import dao.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.Comment;
import pojo.Customer;
import pojo.Order;
import service.CommentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: homeplus
 * @description: comment 接口类的实现类
 * @author: ZEK
 * @create: 2019-04-21 18:02
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Comment> getCommentListByID(int id) {
        List<Order> orderList = orderMapper.getOrderListByHKID(id);
        List<Comment> commentList = new ArrayList<>();
        for (Order order : orderList) {
            int orderID = order.getId();
            int cmID = order.getCmID();
            Customer customer = personMapper.selectCustomerByID(cmID);
            if (commentMapper.getCommentByOrderID(orderID) == null) {
                continue;
            } else {
                Comment comment = commentMapper.getCommentByOrderID(orderID);
                comment.setCommentPublicer(customer.getCmNickname());
                commentList.add(comment);
            }
        }
        return commentList;
    }

    @Override
    public Integer insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }
}
