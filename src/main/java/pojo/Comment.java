package pojo;

import java.util.Date;

/**
 * @program: homeplus
 * @description: 评论的实体类
 * @author: ZEK
 * @create: 2019-04-21 17:49
 **/
public class Comment {
    private int id;
    private String commentContent;
    private int commentStar;
    private int orderID;
    private Date commentTime;
    private String commentPublicer;

    public String getCommentPublicer() {
        return commentPublicer;
    }

    public void setCommentPublicer(String commentPublicer) {
        this.commentPublicer = commentPublicer;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentStar() {
        return commentStar;
    }

    public void setCommentStar(int commentStar) {
        this.commentStar = commentStar;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
