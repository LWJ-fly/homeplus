package service;

import pojo.Comment;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: homeplus
 * @description: comment 的接口类
 * @author: ZEK
 * @create: 2019-04-21 18:00
 **/
public interface CommentService {

    /**
     * 通过家政人员的编号来获得对应的评价
     * @param id 家政人员的编号
     * @return 评价列表
     */
    List<Comment> getCommentListByID(int id);

    /**
     * 发布评价
     * @param comment 评价信息
     * @return 受影响的行数
     */
    Integer insertComment (Comment comment);
}
