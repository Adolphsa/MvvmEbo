package com.lucas.ebo.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.SplashLoginActivityViewModel;
import com.lucas.ebo.databinding.ActivitySplashLoginBinding;
import com.lucas.ebo.ui.activity.account.LoginByPhoneAndCodeActivity;
import com.lucas.ebo.ui.activity.account.SignUpByPhoneActivity;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/4/1 18:47
 *
 * Description:
 *
 */
public class SplashLoginActivity extends BaseActivity {

    private static final String TAG = "SplashLoginActivity";

    private SplashLoginActivityViewModel mSplashLoginActivityViewModel;
    private ActivitySplashLoginBinding mActivitySplashLoginBinding;

    @Override
    protected void initViewModel() {
        mSplashLoginActivityViewModel = getActivityViewModel(SplashLoginActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_splash_login, mSplashLoginActivityViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivitySplashLoginBinding = (ActivitySplashLoginBinding)getBinding();

        initAnimation();
    }

    private void initAnimation()
    {
        //申明一个动画
        Animation ani= AnimationUtils.loadAnimation(this,R.anim.logo_toming);
        //为空间绑定动画
        mActivitySplashLoginBinding.splashBtnGuest.setAnimation(ani);
        mActivitySplashLoginBinding.splashImageLogo.setAnimation(ani);
        mActivitySplashLoginBinding.splashBottomLl.setVisibility(View.GONE);
        mActivitySplashLoginBinding.splashBottomLl.postDelayed(new Runnable() {
            @Override
            public void run() {
                //-1.0表示控件初始值
                TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mHiddenAction.setDuration(1500);
                mActivitySplashLoginBinding.splashBottomLl.startAnimation(mHiddenAction);
                mActivitySplashLoginBinding.splashBottomLl.setVisibility(View.VISIBLE);
            }
        },1500);
    }


    public class ClickProxy {

        public void guestClick() {

        }

        public void signUpClick() {
            LogUtils.d(TAG,"注册通过手机号，国内");
            Intent intent = new Intent(SplashLoginActivity.this, SignUpByPhoneActivity.class);
            startActivity(intent);
        }

        public void loginClick() {
            LogUtils.d(TAG,"点击loginClick");
            Intent intent = new Intent(SplashLoginActivity.this, LoginByPhoneAndCodeActivity.class);
            startActivity(intent);
        }
    }
}
