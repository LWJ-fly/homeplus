package utils;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: homeplus
 * @description: 日期的工具类
 * @author: ZEK
 * @create: 2019-04-18 12:22
 **/
public final class FormatDate {

    /**
     * 将毫秒转换为日期
     * @param millisecond 毫秒
     * @return 日期
     * @throws ParseException
     */
    public static Date mill2Date (long millisecond) throws ParseException {
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(format.format(date));
        date = format.parse(format.format(date));
        return date;
    }

    /**
     * 将字符串转换为日期
     * @param str 字符串
     * @return 日期
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
