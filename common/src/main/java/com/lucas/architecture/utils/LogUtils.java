package com.lucas.architecture.utils;

import androidx.annotation.Nullable;

import com.lucas.common.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by lucas
 * Date: 2020/4/2 12:26
 */
public class LogUtils {

    public static void initLogger() {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                // （可选）要显示的方法行数。 默认2
                .methodCount(3)
                .tag("EBO_LOGGER")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static void d(String tag, String d) {
        Logger.t(tag).d(d);
    }


}
