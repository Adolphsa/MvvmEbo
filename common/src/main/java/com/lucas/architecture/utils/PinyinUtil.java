package com.lucas.architecture.utils;

import static com.github.promeg.pinyinhelper.Pinyin.toPinyin;

/**
 * Created by lucas
 * Date: 2020/4/30 12:21
 */
public class PinyinUtil {

    public static String getPingYin(String inputString) {
        try {
            return toPinyin(inputString, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
