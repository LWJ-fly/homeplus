package service.impl;

import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.HouseKeeper;
import pojo.Type;
import service.IndexService;
import utils.RecommendUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: homeplus
 * @description: IndexService的接口类
 * @author: ZEK
 * @create: 2019-04-11 13:53
 **/

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private HKPersonMapper hkPersonMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Type> getAllType() {
        List<Type> list = new ArrayList<Type>();
        list = indexMapper.getAllType();
        return list;
    }

    @Override
    public List<Type> getAllSmallType(Integer type_id) {
        return indexMapper.getAllSmallType(type_id);
    }

    @Override
    public List<HouseKeeper> getTopHousekeeper(int type) {
        List<Integer> hkIDList = orderMapper.getTopOrderByTypeID(type);
        List<HouseKeeper> list = new ArrayList<>();
        for (int i : hkIDList) {
           HouseKeeper houseKeeper =  hkPersonMapper.getHousekeeperByID(i);
           list.add(houseKeeper);
        }
        for (HouseKeeper hk : list) {
            String hkName = indexMapper.getCompanyNameByID(hk.getCompanyID());
            hk.setCompanyName(hkName);
        }
        return list;
    }

    @Override
    public Integer getTypeID(String param) {
        return typeMapper.getTypeIDByName(param);
    }

    @Override
    public List<HouseKeeper> getRecommend(HttpSession session) {
        List<List<Integer>> recommendMatrix = RecommendUtil.getRecommendResult(getOrderNum(), getTypeSimilarity());
        int typeID = gettypeID(recommendMatrix, session);
        return getTopHousekeeper(typeID);
    }

    /**
     * 获得最相似的种类编号
     * @param recommendMatrix
     * @return 得到最相似的种类
     */
    private int gettypeID (List<List<Integer>> recommendMatrix, HttpSession session) {
        int maxResult = 0;
        int maxResultIndex = 0;
        int n = 0;

        String phone = (String)session.getAttribute("username");
        if (StringUtils.isEmpty(phone)) {
            for (List<Integer> list : recommendMatrix) {
                for (int j = 0; j < list.size(); j ++) {
                    if (list.get(j) > maxResult) {
                        maxResult = list.get(j);
                        maxResultIndex = j;
                    }
                }
            }
        } else {
            int id = userMapper.selectCmIDByPhone(phone);
            n = getIndex(id, orderMapper.getAllCmID());
            for (int j = 0; j < recommendMatrix.get(n).size(); j ++) {
                if (recommendMatrix.get(n).get(j) > maxResult) {
                    maxResult = recommendMatrix.get(n).get(j);
                    maxResultIndex = j;
                }
            }
        }

        // 由于list是有序的，所以可以通过序号查找到种类编号
        int typeID = orderMapper.getAllTypeID().get(maxResultIndex);
        return typeID;
    }

    /**
     * 获得用户和种类的二维矩阵
     * @return 二维矩阵
     */
    private List<List<Integer>> getOrderNum () {
        List<Integer> cmList = orderMapper.getAllCmID();
        List<Integer> typeList = orderMapper.getAllTypeID();

        List<List<Integer>> result = new ArrayList<>();
        for (int cmID : cmList) {
            List<Integer> list = new ArrayList<>();
            for (int typeID : typeList) {
                int singleResult = orderMapper.getOrderNumByTypeIDAndCmID(cmID, typeID);
                list.add(singleResult);
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 获取种类之间的相似度
     * @return 二维矩阵
     */
    private List<List<Integer>> getTypeSimilarity () {
        // 调用上方函数，获取到用户与种类之间的评分矩阵
        List<List<Integer>> orderNum = getOrderNum();

        // 作为种类相似度的返回矩阵
        List<List<Integer>> result = new ArrayList<>();

        // 种类与种类之间的关系，假如 种类1 和 种类2 同时存在于用户A、用户B和用户C中，那么则种类之间的相似度为3
        for (int i = 0; i < orderNum.get(0).size(); i ++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < orderNum.get(0).size(); j ++) {
                int single = 0;
                // 这里表示通过遍历用户，来取的种类与种类之间的相似度
                for (int n = 0; n < orderNum.size(); n ++) {
                    if (orderNum.get(n).get(i) > 0 && orderNum.get(n).get(j) > 0) {
                        single ++;
                    }
                }
                list.add(single);
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 获取 list 中某个元素的下标
     * @return 下标
     */
    private int getIndex (int num, List<Integer> list) {
        int index = 0;
        for (int i = 0; i < list.size(); i ++) {
            if (list.get(i) == num) {
                index = i;
            }
        }
        return index;
    }
}
