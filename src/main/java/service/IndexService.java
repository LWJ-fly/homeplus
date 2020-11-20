package service;

import pojo.HouseKeeper;
import pojo.Type;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IndexService {

    List<Type> getAllType();

    List<Type> getAllSmallType(Integer type_id);

    List<HouseKeeper> getTopHousekeeper(int type);

    Integer getTypeID (String param);

    /**
     * 获取推荐的家政人员列表
     * @return
     */
    List<HouseKeeper> getRecommend (HttpSession session);
}
