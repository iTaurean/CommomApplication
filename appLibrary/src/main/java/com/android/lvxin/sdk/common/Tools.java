package com.android.lvxin.sdk.common;

/**
 * @ClassName: Tools
 * @Description: TODO
 * @Author: lvxin
 * @Date: 11/27/15 16:15
 */
public class Tools {

    public static boolean isStrEmpty(String str) {
        if (null == str || "".equals(str.trim()) || "null".equals(str)) {
            return true;
        }
        return false;
    }
}
