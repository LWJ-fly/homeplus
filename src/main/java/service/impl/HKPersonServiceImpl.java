package service.impl;

import dao.CompanyMapper;
import dao.HKPersonMapper;
import dao.IndexMapper;
import dao.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.HouseKeeper;
import service.HKPersonService;
import service.exception.UserNoLoginException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: homeplus
 * @description: HKPersonService 的实现类
 * @author: ZEK
 * @create: 2019-04-20 11:36
 **/
@Service
public class HKPersonServiceImpl implements HKPersonService{

    @Autowired
    private HKPersonMapper hkPersonMapper;
    @Autowired
    private IndexMapper indexMapper;

    @Override
    public HouseKeeper selectHKByPhone(HttpSession session) {
        String phone = (String)session.getAttribute("username");
        if (StringUtils.isEmpty(phone)) {
            throw new UserNoLoginException("用户未登录");
        } else {
            return hkPersonMapper.selectHKByPhone(phone);
        }
    }

    @Override
    public Integer updateHKPerson(HouseKeeper houseKeeper) {
        return hkPersonMapper.updateHK(houseKeeper);
    }

    @Override
    public Integer certifyHK(HouseKeeper houseKeeper) {
        return hkPersonMapper.certifyHK(houseKeeper);
    }

    @Override
    public Integer getCertifyStatus(String phone) {
        return hkPersonMapper.selectHKStatusByPhone(phone);
    }

    @Override
    public List<HouseKeeper> getHKListFuzzySelect(Integer typeid) {
        String typeIDStr = typeid.toString();
        List<HouseKeeper> list = hkPersonMapper.getHKListFuzzySelect(typeIDStr);
        for (HouseKeeper hk : list) {
            if (StringUtils.isEmpty(hk.getCompanyID())) {
                hk.setCompanyName("个人");
            } else {
                hk.setCompanyName(indexMapper.getCompanyNameByID(hk.getCompanyID()));
            }
        }
        return list;
    }

    @Override
    public HouseKeeper getHousekeeperByID(Integer id) {
        HouseKeeper hk = hkPersonMapper.getHousekeeperByID(id);
        hk.setCompanyName(indexMapper.getCompanyNameByID(hk.getCompanyID()));
        return hk;
    }

    @Override
    public void updateHousekeeperInfo(HttpSession session, HouseKeeper houseKeeper) {
        String phone = (String)session.getAttribute("username");
        if (StringUtils.isEmpty(phone)) {
            throw new UserNoLoginException("用户未登录");
        } else {
            houseKeeper.setHkPhone(phone);
            hkPersonMapper.updateHousekeeperInfo(houseKeeper);
        }
    }
}
