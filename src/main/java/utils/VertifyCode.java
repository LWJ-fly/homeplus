package utils;

import java.util.Random;

/**
 * @program: homeplus
 * @description: 生成随机验证码的内容
 * @author: ZEK
 * @create: 2019-03-31 17:28
 **/
public final class VertifyCode {

    private static String [] codes = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    public static String create () {
        String code = new String();
        Random rand = new Random();
        code = codes[rand.nextInt(53)] + codes[rand.nextInt(53)] + codes[rand.nextInt(53)] + codes[rand.nextInt(53)];
        return code;
    }

    public static void main(String [] args) {
        String codes = VertifyCode.create();
        System.out.println(codes);
    }
}
