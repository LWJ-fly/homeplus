package dao;

import org.apache.ibatis.annotations.Param;
import pojo.HouseKeeper;
import pojo.Type;

import java.util.List;

public interface IndexMapper {

    /**
     * 获取所有的大类别
     * @return 类别的集合
     */
    List<Type> getAllType();

    /**
     * 获取大类别下的小类别
     * @param type_id 大类别的 id
     * @return 小类别的集合
     */
    List<Type> getAllSmallType(Integer type_id);

    /**
     * 获取一些顶级的家政人员
     * @return 家政人员列表
     */
    List<HouseKeeper> getTopHouseKeeper();

    /**
     * 通过公司的 id 来获取公司的名字
     * @param companyID
     * @return
     */
    String getCompanyNameByID(@Param("id") Integer companyID);
}
