package com.android.lvxin.common;

import android.content.Context;

/**
 * @ClassName: Tools
 * @Description: TODO
 * @Author: lvxin
 * @Date: 11/27/15 16:15
 */
public class Tools {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param context
     * @param pxValue（DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean isStrEmpty(String str) {
        if (null == str || "".equals(str.trim()) || "null".equals(str)) {
            return true;
        }
        return false;
    }
}
