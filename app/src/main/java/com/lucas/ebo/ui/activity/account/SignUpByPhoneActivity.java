package com.lucas.ebo.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.lucas.architecture.utils.IntentUtils;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.RegexUtils;
import com.lucas.architecture.utils.ToastUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.request.AccountRequestViewModel;
import com.lucas.ebo.bridge.state.RegisterSetPasswordViewModel;
import com.lucas.ebo.bridge.state.SignUpActivityViewModel;
import com.lucas.ebo.data.bean.request.AuthCodeRequestBean;
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
    private AccountRequestViewModel mAccountRequestViewModel;

    //计时线程
    private CountDownTimer mCountDownTimer;
    //30s 倒计时
    private final int ALL_TIME = 30000;

    @Override
    protected void initViewModel() {
        mSignUpActivityViewModel = getActivityViewModel(SignUpActivityViewModel.class);
        mAccountRequestViewModel = getActivityViewModel(AccountRequestViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_sign_up, mSignUpActivityViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.activity, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCountDownTimer = new CountDownTimer(ALL_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mSignUpActivityViewModel.sendCode.set((millisUntilFinished/1000) + " S");
            }

            @Override
            public void onFinish() {
                mSignUpActivityViewModel.sendCode.set("Send Code");
                mSignUpActivityViewModel.sendCodeTextColor.set(R.color.auth_code_color);
                mSignUpActivityViewModel.sendCodeClickable.set(true);
            }
        };

        getSharedViewModel().countryDisplayName.observe(this, countryDisplayName -> {
            mSignUpActivityViewModel.countryDisplayName.setValue(countryDisplayName);
        });
        getSharedViewModel().countryNumber.observe(this, countryNumber ->  {
            mSignUpActivityViewModel.countryNumber.setValue("+" + countryNumber);
        });

    }

    public void onAuthCodeTextChange(String str)
    {
        LogUtils.d(TAG,"输入的验证码为 = " + str);
        if (!TextUtils.isEmpty(str))
        {
            mSignUpActivityViewModel.continueButtonClickable.set(true);
            mSignUpActivityViewModel.continueButtonColor.set(0xffff9632);
            mSignUpActivityViewModel.continueButtonText.set("Continue");
        }else {
            mSignUpActivityViewModel.continueButtonClickable.set(false);
            mSignUpActivityViewModel.continueButtonColor.set(0xffd2d2d2);
            mSignUpActivityViewModel.continueButtonText.set("Sign Up/Login");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null)
        {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
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

        public void sendCode()
        {

            String phoneNumber = mSignUpActivityViewModel.phoneNumber.get();
            if (TextUtils.isEmpty(phoneNumber))
            {
                ToastUtils.showShort("电话号码为空");
                return;
            }
            if (!RegexUtils.isMobileSimple(phoneNumber))
            {
                ToastUtils.showShort("请输入正确的手机号码");
                return;
            }

            LogUtils.d(TAG,"输入的号码为 = " + phoneNumber);

            AuthCodeRequestBean checkParams = new AuthCodeRequestBean();
            checkParams.setPhone_num(phoneNumber);
            checkParams.setPhone_area(mSignUpActivityViewModel.countryNumber.getValue());
            mAccountRequestViewModel.requestCheckParams(checkParams);

            mAccountRequestViewModel.sendCodeBoolean.observe(SignUpByPhoneActivity.this, aBoolean -> {
                LogUtils.d(TAG,"检查号码 = " + aBoolean);
                if (aBoolean)
                {
                    if (mCountDownTimer != null)
                    {
                        mCountDownTimer.start();
                        mSignUpActivityViewModel.sendCodeClickable.set(false);
                        mSignUpActivityViewModel.sendCodeTextColor.set(R.color.divide_color_96);
                    }

                    //发送获取验证码
                    AuthCodeRequestBean authCodeRequestBean = new AuthCodeRequestBean();
                    authCodeRequestBean.setLanguage("zh-hans");
                    authCodeRequestBean.setPhone_num(phoneNumber);
                    authCodeRequestBean.setOperation("register");
                    authCodeRequestBean.setPhone_area(mSignUpActivityViewModel.countryNumber.getValue());
                    mAccountRequestViewModel.requestGetAuthCode(authCodeRequestBean);

                }
            });

        }

        public void registerClick()
        {
            LogUtils.d(TAG,"注册点击");
            Intent intent = new Intent(SignUpByPhoneActivity.this, RegisterSetPasswordActivity.class);
            startActivity(intent);
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
