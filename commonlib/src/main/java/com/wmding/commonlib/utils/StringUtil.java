package com.wmding.commonlib.utils;

import java.util.Random;

/**
 * @author wmding
 * @date 2019/3/29
 * @describe 操作字符串的工具类
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断对象是否为空
     * @param object 一个对象
     * @return 如果 objects 为空返回true, 否则返回false
     */
    public static boolean isNull(Object object) {
        return object == null;
    }


    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int baseLen = base.length();
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(baseLen);
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
