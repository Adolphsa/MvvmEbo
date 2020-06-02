package com.lucas.ebo.utils;

import android.content.Context;
import android.os.Build;

import com.lucas.architecture.utils.LogUtils;

import java.util.Locale;

/**
 * Created by lucas
 * Date: 2020/4/29 15:58
 */
public class LanguageUtils {

    private static final String TAG = "LanguageUtils";

    /**
     * 获得当前系统语言(第二种方法，这种方法需要判断设备是否大于 Android 7.0)
     * @param context context
     * @return zh／en..
     */
    public static String getCurrentLanguage(Context context){
        if (null != context && null != context.getResources() && null != context.getResources().getConfiguration()) {
            Locale locale;  //en_US
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locale = context.getResources().getConfiguration().getLocales().get(0);
            } else {
                locale = context.getResources().getConfiguration().locale;
            }
            if (null != locale) {
                // 获得语言码 en
                return locale.getLanguage();
            }
        }
        return null;
    }

    public static boolean isChinaMain(Context context)
    {
        String currentLanguage = getCurrentLanguage(context);
        LogUtils.d(TAG, "现在的语言是:" + currentLanguage);
        if ("zh".equals(currentLanguage))
        {
            return true;
        }
        return false;
    }


}
