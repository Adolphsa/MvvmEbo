package com.lucas.ebo.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.LoginByPhoneAndCodeActivityViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/4/28 15:53
 *
 * Description: 登录 通过手机号和验证码 国内
 *
 */
public class LoginByPhoneAndCodeActivity extends BaseActivity {

    private static final String TAG = "LoginByPhoneAndCodeActivity";

    private LoginByPhoneAndCodeActivityViewModel mLoginActivityViewModel;


    @Override
    protected void initViewModel() {
        mLoginActivityViewModel = getActivityViewModel(LoginByPhoneAndCodeActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_login, mLoginActivityViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy
    {
        public void back()
        {
            finish();
        }

        public void useAccountPwdLogin()
        {
            LogUtils.d(TAG,"点击useAccountPwdLogin");
            Intent intent = new Intent(LoginByPhoneAndCodeActivity.this, LoginByAccountAndPwdActivity.class);
            startActivity(intent);
        }

    }
}
