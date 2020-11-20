package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Message;
import pojo.ResponseResult;
import service.MessageService;
import service.UserService;
import service.exception.UserNoLoginException;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: homeplus
 * @description: Message 的控制类
 * @author: ZEK
 * @create: 2019-04-20 19:31
 **/
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/getMessageByID")
    @ResponseBody
    public ResponseResult<List<Message>> getMessageByID (
            HttpSession session
    ) {
        ResponseResult<List<Message>> result = new ResponseResult<>();
        List<Message> list = messageService.getMessageByToID(session);
        result.setData(list);
        return result;
    }

    @RequestMapping("/insertMessage")
    @ResponseBody
    public ResponseResult<Void> insertMessage (
            HttpSession session,
            @RequestParam("id") int toID,
            @RequestParam("msgContent") String msgContent
    ) {
        messageService.insertMessage(msgContent, session, toID);
        return new ResponseResult<>();
    }
}
