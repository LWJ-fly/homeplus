package utils;

import java.util.Date;

/**
 * @program: homeplus
 * @description: 计算两个日期之间的天数
 * @author: ZEK
 * @create: 2019-04-17 17:54
 **/
public final class CalcDate {

    public static int calcDays (Date begin, Date end) {
        int days = 0;
        if (end == null) {
            days = 9999;
        } else {
            days = (int) ((end.getTime() - begin.getTime()) / (1000*3600*24));
        }
        return days;
    }
}
