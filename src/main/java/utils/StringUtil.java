package utils;

import java.io.UnsupportedEncodingException;

/**
 * @program: homeplus
 * @description: 处理字符串的工具类
 * @author: ZEK
 * @create: 2019-05-13 17:22
 **/
public class StringUtil {
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
