package service;

import org.apache.ibatis.annotations.Param;
import pojo.HouseKeeper;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface HKPersonService {

    /**
     * 获取家政人员的基本信息
     * @param session
     * @return
     */
    HouseKeeper selectHKByPhone (HttpSession session);

    /**
     * 更新家政人员的基本资料
     * @param houseKeeper 家政人员
     * @return 受影响的行数
     */
    Integer updateHKPerson (HouseKeeper houseKeeper);

    /**
     * 家政人员的认证
     * @param houseKeeper 家政人员的认证资料
     * @return 受影响的行数
     */
    Integer certifyHK (HouseKeeper houseKeeper);

    /**
     * 通过手机号获取认证状态
     * @param phone 手机号
     * @return 认证状态
     */
    Integer getCertifyStatus (String phone);

    /**
     * 根据类型 id 进行模糊查询
     * @param typeid 类型的 id
     * @return 用户列表
     */
    List<HouseKeeper> getHKListFuzzySelect (Integer typeid);


    /**
     * 根据家政人员的编号获取家政人员的信息
     * @param id 家政人员的编号
     * @return 家政人员的信息
     */
    HouseKeeper getHousekeeperByID (Integer id);

    /**
     * 更新家政人员的基本信息
     * @param session
     * @param houseKeeper
     */
    void updateHousekeeperInfo (HttpSession session, HouseKeeper houseKeeper);

}
