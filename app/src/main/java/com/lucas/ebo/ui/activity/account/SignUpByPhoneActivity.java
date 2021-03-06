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
import com.lucas.ebo.bridge.request.AccountRequestViewModel;
import com.lucas.ebo.bridge.state.SignUpActivityViewModel;
import com.lucas.ebo.data.bean.request.AuthCodeRequestBean;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.country.CountryPickActivity;

import static com.lucas.ebo.utils.ColorConstant.COLOR_GRAY_D2;
import static com.lucas.ebo.utils.ColorConstant.COLOR_ORANGE_96;


/**
 * Created by lucas
 * <p>
 * Date: 2020/4/27 14:57
 * <p>
 * Description:  国内注册 通过手机号
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
                mSignUpActivityViewModel.sendCode.set((millisUntilFinished / 1000) + " S");
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
        getSharedViewModel().countryNumber.observe(this, countryNumber -> {
            mSignUpActivityViewModel.countryNumber.setValue("+" + countryNumber);
        });

        mAccountRequestViewModel.sendCodeBoolean.observe(SignUpByPhoneActivity.this, aBoolean -> {
            if (aBoolean) {

                dismissLoading();

                if (mCountDownTimer != null) {
                    mCountDownTimer.start();
                    mSignUpActivityViewModel.sendCodeClickable.set(false);
                    mSignUpActivityViewModel.sendCodeTextColor.set(R.color.divide_color_96);
                }

                String phoneNumber = mSignUpActivityViewModel.phoneNumber.get();
                LogUtils.d(TAG, "输入的号码为 = " + phoneNumber);

                //发送获取验证码
                AuthCodeRequestBean authCodeRequestBean = new AuthCodeRequestBean();
                authCodeRequestBean.setLanguage("zh-hans");
                authCodeRequestBean.setPhone_num(phoneNumber);
                authCodeRequestBean.setOperation("register");
                authCodeRequestBean.setPhone_area(mSignUpActivityViewModel.countryNumber.getValue());
                mAccountRequestViewModel.requestGetAuthCode(authCodeRequestBean);
            }else {
                dismissLoading();
            }
        });
    }

    public void onAuthCodeTextChange(String str) {
        if (!TextUtils.isEmpty(str)) {
            mSignUpActivityViewModel.continueButtonClickable.set(true);
            mSignUpActivityViewModel.continueButtonColor.set(COLOR_ORANGE_96);
            mSignUpActivityViewModel.continueButtonText.set("Continue");
        } else {
            mSignUpActivityViewModel.continueButtonClickable.set(false);
            mSignUpActivityViewModel.continueButtonColor.set(COLOR_GRAY_D2);
            mSignUpActivityViewModel.continueButtonText.set("Sign Up/Login");
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

    public class ClickProxy {

        public void back() {
            finish();
        }

        public void toCountryPick() {
            Intent intent = new Intent(SignUpByPhoneActivity.this, CountryPickActivity.class);
            startActivity(intent);
        }

        public void sendCode() {
            showLoading();
            String phoneNumber = mSignUpActivityViewModel.phoneNumber.get();
            if (TextUtils.isEmpty(phoneNumber)) {
                SmartToast.showInCenter("电话号码为空");
                dismissLoading();
                return;
            }
            if (!RegexUtils.isMobileSimple(phoneNumber)) {
                SmartToast.showInCenter("请输入正确的手机号码");
                dismissLoading();
                return;
            }

            //检查号码是否合法
            AuthCodeRequestBean checkParams = new AuthCodeRequestBean();
            checkParams.setPhone_num(phoneNumber);
            checkParams.setPhone_area(mSignUpActivityViewModel.countryNumber.getValue());
            mAccountRequestViewModel.requestCheckParams(checkParams);
        }

        public void registerClick() {

            String phoneNumber = mSignUpActivityViewModel.phoneNumber.get();
            String phoneAuthCode = mSignUpActivityViewModel.authCode.get();

            if (TextUtils.isEmpty(phoneNumber)) {
                SmartToast.showInCenter("电话号码为空");
                return;
            }
            if (!RegexUtils.isMobileSimple(phoneNumber)) {
                SmartToast.showInCenter("请输入正确的手机号码");
                return;
            }

            getSharedViewModel().phoneNumber.setValue(phoneNumber);
            getSharedViewModel().phoneAuthCode.setValue(phoneAuthCode);

            Intent intent = new Intent(SignUpByPhoneActivity.this, RegisterSetPasswordActivity.class);
            startActivity(intent);
        }

        public void useEmailSignUp() {
            LogUtils.d(TAG, "用邮箱注册");
            //to Email sign up
            Intent intent = new Intent(SignUpByPhoneActivity.this, SignUpByEmailActivity.class);
            startActivity(intent);
        }

        public void toLogin() {
            LogUtils.d(TAG, "登录点击");
            //to login by phone and code
            Intent intent = new Intent(SignUpByPhoneActivity.this, LoginByPhoneAndCodeActivity.class);
            startActivity(intent);
        }
    }

}
