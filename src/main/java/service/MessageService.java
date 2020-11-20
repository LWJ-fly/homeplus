package service;

import pojo.Message;
import service.exception.NoMessagePermission;
import service.exception.UserNoLoginException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MessageService {

    /**
     * 通过用户的编号来获得消息的内容
     * @param session 通过用户存放在session中的手机号获得编号
     * @return 消息的列表
     */
    List<Message> getMessageByToID (HttpSession session);

    /**
     * 插入消息
     * @param msgContent 消息内容
     * @param session
     * @param toID 接收者的编号
     * @return 受影响的行数
     * @throws UserNoLoginException 用户未登录异常
     * @throws NoMessagePermission 没有发送留言的权限
     */
    Integer insertMessage (String msgContent, HttpSession session, int toID) throws UserNoLoginException, NoMessagePermission;
}
