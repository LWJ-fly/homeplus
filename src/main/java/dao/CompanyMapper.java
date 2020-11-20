package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Company;

import java.util.List;

/**
 * @program: homeplus
 * @description: 公司的mapper类
 * @author: ZEK
 * @create: 2019-04-20 19:15
 **/
public interface CompanyMapper {

    /**
     * 通过状态获得认证的公司
     * @param status 公司的认证状态
     * @return 公司列表
     */
    List<Company> getCompanyByStatus (@Param("status") int status);

    /**
     * 认证公司
     * @param company 公司的认证信息
     * @return 受影响的行数
     */
    Integer certifyCompany (Company company);

    /**
     * 获得上传认证资料的公司
     * @return 公司列表
     */
    List<Company> getCompanyListByStatus ();

    /**
     * 通过公司编号查询
     * @param id 公司编号
     * @return 公司信息
     */
    Company getCompanyByID (@Param("id") int id);

    /**
     * 更改公司的认证状态
     * @param id 公司编号
     * @return 受影响行数
     */
    Integer updateCompanyStatusByID (@Param("id") int id, @Param("status") int status);
}
