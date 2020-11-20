package service.impl;

import dao.HKPersonMapper;
import dao.MessageMapper;
import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.Message;
import service.MessageService;
import service.exception.NoMessagePermission;
import service.exception.UserNoLoginException;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @program: homeplus
 * @description: MessageService的实现类
 * @author: ZEK
 * @create: 2019-04-20 20:03
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HKPersonMapper hkPersonMapper;

    /**
     * unreadStatus 表示未读状态
     */
    private final int unreadStatus = 0;
    /**
     * readStatus 表示已读
     */
    private final int readStatus = 1;

    @Override
    public List<Message> getMessageByToID(HttpSession session) {
        String phone = (String)session.getAttribute("username");
        if (StringUtils.isEmpty(phone)) {
            throw new UserNoLoginException("用户未登录!");
        } else {
            int id = userMapper.selectHKIDByPhone(phone);
            return messageMapper.getMessageByToID(id);
        }
    }

    @Override
    public Integer insertMessage(String msgContent, HttpSession session, int toID) throws UserNoLoginException, NoMessagePermission {
        String phone = (String)session.getAttribute("username");
        String toPhone = hkPersonMapper.getHousekeeperByID(toID).getHkPhone();
        if (StringUtils.isEmpty(phone)) {
            throw new UserNoLoginException("用户未登录！");
        } else if (phone.equals(toPhone) ) {
            throw new NoMessagePermission("不能给自己留言！");
        } else {
            Message message = new Message();
            int fromID = userMapper.selectCmIDByPhone(phone);
            Date now = new Date();
            message.setContent(msgContent);
            message.setCreatedTime(now);
            message.setFromID(fromID);
            message.setToID(toID);
            message.setMessageStatus(unreadStatus);
            return  messageMapper.insertMessage(message);
        }
    }
}
