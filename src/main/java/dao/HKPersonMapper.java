package dao;

import org.apache.ibatis.annotations.Param;
import pojo.HouseKeeper;

import java.util.List;

public interface HKPersonMapper {

    /**
     * 根据手机号来获取家政人员的信息
     * @param phone 手机号
     * @return 家政人员的信息
     */
    HouseKeeper selectHKByPhone (@Param("phone") String phone);

    /**
     * 通过家政人员的手机号获得家政人员的认证状态
     * @param phone 家政人员的手机号
     * @return 家政人员的认证状态
     */
    Integer selectHKStatusByPhone (@Param("phone") String phone);


    /**
     * 更新家政人员的基本资料
     * @param hk 家政人员
     * @return 受影响的行数
     */
    Integer updateHK (HouseKeeper hk);

    /**
     * 家政人员的认证
     * @param hk 家政人员的资料
     * @return 受影响的行数
     */
    Integer certifyHK (HouseKeeper hk);

    /**
     * 根据家政人员的服务类型进行模糊查询
     * @param typeID 服务类型
     * @return 家政人员列表
     */
    List<HouseKeeper> getHKListFuzzySelect (@Param("typeID") String typeID);

    /**
     * 通过家政人员的编号获取家政人员的信息
     * @param id 家政人员的编号
     * @return 家政人员的信息
     */
    HouseKeeper getHousekeeperByID (@Param("id") int id);

    /**
     * 根据手机号修改密码
     * @param phone 手机号
     * @param password 新密码
     * @return
     */
    Integer updatePasswordByPhone (@Param("phone") String phone, @Param("newPassword") String password);

    /**
     * 获取所有上传资料的家政人员
     * @return 家政人员集合
     */
    List<HouseKeeper> getAllHousekeeperByStatus ();

    /**
     * 根据家政人员编号更改人员的认证状态
     * @param id 家政人员编号
     * @param status 人员的认证状态
     * @return 受影响的行数
     */
    Integer updateHousekeeperStatusByID (@Param("id") int id, @Param("status") int status);

    /**
     * 修改家政人员的基本信息
     * @param houseKeeper 家政人员信息
     * @return 受影响行数
     */
    Integer updateHousekeeperInfo (HouseKeeper houseKeeper);
}
