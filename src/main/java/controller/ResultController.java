package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.HouseKeeper;
import pojo.ResponseResult;
import service.HKPersonService;

import java.util.List;

/**
 * @program: homeplus
 * @description: 搜索结果的控制类
 * @author: ZEK
 * @create: 2019-04-21 10:55
 **/
@Controller
@RequestMapping("/result")
public class ResultController extends BaseController {

    @Autowired
    private HKPersonService hkPersonService;

    @RequestMapping("/loadSearchResult")
    @ResponseBody
    public ResponseResult<List<HouseKeeper>> loadSearchResult (
            @RequestParam("param") Integer param
    ) {
        ResponseResult<List<HouseKeeper>> result = new ResponseResult<>();
        List<HouseKeeper> list = hkPersonService.getHKListFuzzySelect(param);
        result.setData(list);
        return result;
    }


}
