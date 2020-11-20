package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Message;

import java.util.List;

public interface MessageMapper {

    /**
     * 根据收件人的 id 来获得消息内容
     * @param id 收件人的 id
     * @return 消息列表
     */
    List<Message> getMessageByToID (@Param("toID") int id);

    /**
     * 插入消息数据
     * @param message 消息数据
     * @return 受影响的行数
     */
    Integer insertMessage (Message message);
}
