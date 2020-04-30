package com.lucas.ebo;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.lucas.architecture.http.HttpManager;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by lucas
 * Date: 2020/4/1 18:31
 */
public class EboApplication extends Application implements ViewModelStoreOwner {

    private static EboApplication instance;
    public static EboApplication getInstance() {
        return instance;
    }

    //借助 Application 来管理一个应用级 的 SharedViewModel，
    // 实现全应用范围内的 生命周期安全 且 事件源可追溯的 视图控制器 事件通知。

    private ViewModelStore mAppViewModelStore;

    private ViewModelProvider.Factory mFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        mAppViewModelStore = new ViewModelStore();

        Utils.init(this);
        initLogUtils();
    }

    /**
     * init LogUtils
     */
    private void initLogUtils()
    {
        LogUtils.initLogger();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((EboApplication) activity.getApplicationContext(),
                ((EboApplication) activity.getApplicationContext()).getAppFactory(activity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    private Activity checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
        return activity;
    }
}
