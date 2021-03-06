/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lucas.ebo.ui.base;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.coder.zzq.smartshow.dialog.LoadingDialog;
import com.lucas.architecture.data.manager.NetworkStateManager;
import com.lucas.architecture.utils.AdaptScreenUtils;
import com.lucas.architecture.utils.ScreenUtils;
import com.lucas.architecture.utils.StatusBarUtil;
import com.lucas.ebo.BR;
import com.lucas.ebo.EboApplication;
import com.lucas.ebo.bridge.callback.SharedViewModel;

/**
 * Create by KunMinX at 19/8/1
 */
public abstract class BaseActivity extends AppCompatActivity {

    private SharedViewModel mSharedViewModel;
    private ViewModelProvider mActivityProvider;
    private ViewDataBinding mBinding;
//    private TextView mTvStrictModeTip;

    private LoadingDialog mLoadingDialog;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    /**
     * TODO tip: 警惕使用。非必要情况下，尽可能不在子类中拿到 binding 实例乃至获取 view 实例。使用即埋下隐患。
     * 目前的方案是在 debug 模式下，对获取实例的情况给予提示。
     * <p>
     * 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
     *
     * @return
     */
    protected ViewDataBinding getBinding() {
//        if (isDebug() && mBinding != null) {
//            if (mTvStrictModeTip == null) {
//                mTvStrictModeTip = new TextView(getApplicationContext());
//                mTvStrictModeTip.setAlpha(0.5f);
//                mTvStrictModeTip.setTextSize(16);
//                mTvStrictModeTip.setBackgroundColor(Color.WHITE);
//                mTvStrictModeTip.setText(R.string.debug_activity_databinding_warning);
//                ((ViewGroup) mBinding.getRoot()).addView(mTvStrictModeTip);
//            }
//        }
        return mBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setTranslucent(this);
        StatusBarUtil.setLightMode(this);

        mSharedViewModel = ((EboApplication) getApplicationContext()).getAppViewModelProvider(this).get(SharedViewModel.class);

        getLifecycle().addObserver(NetworkStateManager.getInstance());

        initViewModel();
        DataBindingConfig dataBindingConfig = getDataBindingConfig();

        //TODO tip: DataBinding 严格模式：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这样的方式，来彻底解决 视图调用的一致性问题，
        // 如此，视图刷新的安全性将和基于函数式编程的 Jetpack Compose 持平。

        // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910

        if (dataBindingConfig != null)
        {
            ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
            binding.setLifecycleOwner(this);
            binding.setVariable(BR.vm, dataBindingConfig.getStateViewModel());
            SparseArray bindingParams = dataBindingConfig.getBindingParams();
            for (int i = 0, length = bindingParams.size(); i < length; i++) {
                binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
            }
            mBinding = binding;
        }

    }

    public boolean isDebug() {
        return getApplicationContext().getApplicationInfo() != null &&
                (getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    protected void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(int stringRes) {
        showLongToast(getApplicationContext().getString(stringRes));
    }

    protected void showShortToast(int stringRes) {
        showShortToast(getApplicationContext().getString(stringRes));
    }

    protected void showLoading()
    {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog()
                    .large()
                    .withMsg(true)
                    .message("正在加载");
        }
        mLoadingDialog.showInActivity(this);
    }

    protected void dismissLoading()
    {
        if (mLoadingDialog != null)
        {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }

    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }


}
