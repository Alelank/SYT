package com.yiran.syt.common.utils;

/**
 * Created by Ale on 2021/4/17
 */
public class Utils {

    public Utils() {

    }

    public static String trim(Object v) {
        return v != null ? v.toString().trim() : "";
    }

    public static boolean in(int v, int... data) {
        if (data == null) {
            return false;
        }
        int len = data.length;
        int[] d = data;
        for (int var = 0; var < len; ++var) {
            int i = d[var];
            if (i == v) {
                return true;
            }
        }
        return false;
    }

    public static boolean inStr(String v, String... data) {
        v = trim(v);
        if ("".equals(v)) {
            return false;
        }
        String[] d = data;
        int len = data.length;

        for (int var = 0; var < len; ++var) {
            String s = d[var];
            if (v.equals(trim(s))) {
                return true;
            }
        }
        return false;
    }
}
