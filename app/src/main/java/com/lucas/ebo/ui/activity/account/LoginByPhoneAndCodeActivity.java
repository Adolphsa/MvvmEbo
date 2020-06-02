package com.lucas.ebo.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.RegexUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.LoginByPhoneAndCodeActivityViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.country.CountryPickActivity;

import static com.lucas.ebo.utils.ColorConstant.COLOR_GRAY_D2;
import static com.lucas.ebo.utils.ColorConstant.COLOR_ORANGE_96;

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

    //计时线程
    private CountDownTimer mCountDownTimer;
    //30s 倒计时
    private final int ALL_TIME = 30000;


    @Override
    protected void initViewModel() {
        mLoginActivityViewModel = getActivityViewModel(LoginByPhoneAndCodeActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_login, mLoginActivityViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.activity, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //从国家选择Activity获取国家名称和国家码
        getSharedViewModel().countryDisplayName.observe(this, countryDisplayName -> {
            mLoginActivityViewModel.countryDisplayName.setValue(countryDisplayName);
        });
        getSharedViewModel().countryNumber.observe(this, countryNumber -> {
            mLoginActivityViewModel.countryNumber.setValue("+" + countryNumber);
        });

        //send code
        mCountDownTimer = new CountDownTimer(ALL_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mLoginActivityViewModel.sendCode.set((millisUntilFinished / 1000) + " S");
            }

            @Override
            public void onFinish() {
                mLoginActivityViewModel.sendCode.set("Send Code");
                mLoginActivityViewModel.sendCodeTextColor.set(R.color.auth_code_color);
                mLoginActivityViewModel.sendCodeClickable.set(true);
            }
        };

    }

    public void onAuthCodeTextChange(String str) {
        LogUtils.d(TAG, "输入的验证码为 = " + str);
        if (!TextUtils.isEmpty(str)) {
            mLoginActivityViewModel.continueButtonClickable.set(true);
            mLoginActivityViewModel.continueButtonColor.set(COLOR_ORANGE_96);
            mLoginActivityViewModel.continueButtonText.set("Continue");
        } else {
            mLoginActivityViewModel.continueButtonClickable.set(false);
            mLoginActivityViewModel.continueButtonColor.set(COLOR_GRAY_D2);
            mLoginActivityViewModel.continueButtonText.set("Sign Up/Login");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public class ClickProxy
    {
        public void back()
        {
            finish();
        }

        public void toCountryPick() {
            Intent intent = new Intent(LoginByPhoneAndCodeActivity.this, CountryPickActivity.class);
            startActivity(intent);
        }

        public void sendCode()
        {
            String phoneNumber = mLoginActivityViewModel.phoneNumber.get();
            if (TextUtils.isEmpty(phoneNumber)) {
                SmartToast.showInCenter("电话号码为空");
                return;
            }
            if (!RegexUtils.isMobileSimple(phoneNumber)) {
                SmartToast.showInCenter("请输入正确的手机号码");
                return;
            }
        }

        public void loginClick()
        {
            String phoneNumber = mLoginActivityViewModel.phoneNumber.get();
            if (TextUtils.isEmpty(phoneNumber)) {
                SmartToast.showInCenter("电话号码为空");
                return;
            }
            if (!RegexUtils.isMobileSimple(phoneNumber)) {
                SmartToast.showInCenter("请输入正确的手机号码");
                return;
            }
        }

        public void useAccountPwdLogin()
        {
            LogUtils.d(TAG,"点击useAccountPwdLogin");
            Intent intent = new Intent(LoginByPhoneAndCodeActivity.this, LoginByAccountAndPwdActivity.class);
            startActivity(intent);
        }

    }
}
