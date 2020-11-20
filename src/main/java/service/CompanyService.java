package service;

import pojo.Company;

import java.util.List;

public interface CompanyService {

    /**
     * 通过公司的状态来获得公司的列表
     * @param status 公司的状态
     * @return 公司的列表
     */
    List<Company> getCompanyByStatus (int status);

    /**
     * 公司认证
     * @param company 公司的认证信息
     * @return 受影响的行数
     */
    Integer certifyCompany (Company company);
}
