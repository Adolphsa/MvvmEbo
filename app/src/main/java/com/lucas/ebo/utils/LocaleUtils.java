package com.lucas.ebo.utils;

import android.content.Context;
import android.text.TextUtils;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.PinyinUtil;
import com.lucas.ebo.ui.country.AdapterItemCountry;
import com.lucas.ebo.ui.country.AdapterItemLetter;
import com.lucas.ebo.ui.country.QuickMultipleEntity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;

/**
 * Created by lucas
 * Date: 2020/4/29 14:55
 * locale util
 */
public class LocaleUtils {

    private static final String TAG = "LocaleUtils";
    /**
     * 获取 item 数据集合
     * 耗时操作，后台线程调用
     *
     * @return
     */
    public static List<QuickMultipleEntity> getCountryData(Context context) {
        List<QuickMultipleEntity> dataList = new ArrayList<QuickMultipleEntity>();
        HashSet<AdapterItemLetter> letterSet = new HashSet<>();
        //获取国家代码数据
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.createInstance(context);
        Set<String> set = phoneNumberUtil.getSupportedRegions();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String compactCountry = iterator.next();
            //根据当前系统语言，输出 compactCountry（国家编码）对应的 local 对象。
            Locale locale = new Locale(LanguageUtils.getCurrentLanguage(context), compactCountry);
            //获取详细的国家名称 China
            String displayCountry = locale.getDisplayCountry();
            if (!TextUtils.isEmpty(displayCountry)) {
                if (displayCountry.equals("中国")) {
                    displayCountry = displayCountry + "大陆";
                } else if (displayCountry.equals("香港") || displayCountry.equals("澳门") || displayCountry.equals("台湾")) {
                    displayCountry = displayCountry + "（中国）";
                } else if (displayCountry.equalsIgnoreCase("China")) {
                    displayCountry = displayCountry + " mainland";
                }
            }
            //获取国家代码 86
            int countryCode = phoneNumberUtil.getCountryCodeForRegion(compactCountry);

            AdapterItemCountry bean = new AdapterItemCountry();
            bean.setCountryNumber(countryCode);
            bean.setCountryName(displayCountry);
            bean.setCountrySimpleName(compactCountry);
            bean.setSelect(false);
//            LogUtils.d(TAG, "国家 = " + displayCountry + ",国家号 = " + countryCode);

            //获取拼音
            String pinyin = PinyinUtil.getPingYin(displayCountry);
            QuickMultipleEntity quickMultipleEntity = new QuickMultipleEntity(QuickMultipleEntity.COUNTRY_TEXT, bean, pinyin);

            if (!TextUtils.isEmpty(pinyin))
            {
                char letter = pinyin.charAt(0);
                if(!isLetter(letter))
                {
                    letter = 35;
                }
                letterSet.add(new AdapterItemLetter(letter + ""));
            }

            dataList.add(quickMultipleEntity);
        }

        LogUtils.d(TAG, "字母set size = " + letterSet.size());

        for (AdapterItemLetter adapterItemLetter : letterSet) {
            dataList.add(new QuickMultipleEntity(QuickMultipleEntity.LETTER, adapterItemLetter, adapterItemLetter.getLetter()));
        }

        //排序
        sort(dataList, context);
        return dataList;
    }

    /**
     * 按照字母（国际化）排序数据集合
     * 根据不同国家排序，中国就按拼音，英文就按英文字母排序
     * @param dataList data list
     */
    private static void sort(List<QuickMultipleEntity> dataList, Context context) {
        final Locale locale = new Locale(LanguageUtils.getCurrentLanguage(context));
        Collections.sort(dataList, new Comparator<QuickMultipleEntity>() {
            @Override
            public int compare(QuickMultipleEntity lhs, QuickMultipleEntity rhs) {
                String display1 = lhs.getPinyin();
                String display2 = rhs.getPinyin();
                //根据不同国家排序，中国就按拼音，英文就按英文字母排序
                return Collator.getInstance(locale).compare(display1, display2);
            }
        });
    }

    private static boolean isLetter(char letter) {
        return 'a' <= letter && 'z' >= letter || 'A' <= letter && 'Z' >= letter;
    }
}
