package com.lucas.ebo.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.SignUpActivityViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.country.CountryPickActivity;

/**
 * Created by lucas
 *
 * Date: 2020/4/27 14:57
 *
 * Description:  国内注册 通过手机号
 *
 */
public class SignUpByPhoneActivity extends BaseActivity {

    private static final String TAG = "SignUpActivity";

    private SignUpActivityViewModel mSignUpActivityViewModel;

    @Override
    protected void initViewModel() {
        mSignUpActivityViewModel = getActivityViewModel(SignUpActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_sign_up, mSignUpActivityViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSharedViewModel().countryDisplayName.observe(this, countryDisplayName -> {
            mSignUpActivityViewModel.countryDisplayName.setValue(countryDisplayName);
        });
        getSharedViewModel().countryNumber.observe(this, countryNumber ->  {
            mSignUpActivityViewModel.countryNumber.setValue("+" + countryNumber + " |");
        });
    }


    public class ClickProxy{

        public void back()
        {
            finish();
        }

        public void toCountryPick()
        {
            Intent intent = new Intent(SignUpByPhoneActivity.this, CountryPickActivity.class);
            startActivity(intent);
        }

        public void registerClick()
        {
            LogUtils.d(TAG,"注册点击");
            mSignUpActivityViewModel.registerByEmail("test3@bccto.me", "z123456z", "z123456z");
        }

        public void useEmailSignUp()
        {
            LogUtils.d(TAG,"用邮箱登录点击");
            //to Email sign up
            Intent intent = new Intent(SignUpByPhoneActivity.this, SignUpByEmailActivity.class);
            startActivity(intent);
        }

        public void toLogin()
        {
            LogUtils.d(TAG,"登录点击");
            //to login by phone and code
            Intent intent = new Intent(SignUpByPhoneActivity.this, LoginByPhoneAndCodeActivity.class);
            startActivity(intent);
        }
    }

}
