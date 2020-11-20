package service.impl;

import dao.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Company;
import service.CompanyService;

import java.util.List;

/**
 * @program: homeplus
 * @description: CompanyService的实现类
 * @author: ZEK
 * @create: 2019-04-20 19:28
 **/
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> getCompanyByStatus(int status) {
        return companyMapper.getCompanyByStatus(status);
    }

    @Override
    public Integer certifyCompany(Company company) {
        return companyMapper.certifyCompany(company);
    }
}
