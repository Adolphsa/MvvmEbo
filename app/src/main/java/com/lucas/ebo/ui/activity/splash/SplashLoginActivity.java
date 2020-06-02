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
import com.lucas.ebo.ui.activity.account.LoginByAccountForeignActivity;
import com.lucas.ebo.ui.activity.account.LoginByPhoneAndCodeActivity;
import com.lucas.ebo.ui.activity.account.SignUpByEmailForeignActivity;
import com.lucas.ebo.ui.activity.account.SignUpByPhoneActivity;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.utils.LanguageUtils;

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

            //如果当前语言是中文，那么就跳转到通过电话注册的页面，
            //如果不是中文，就跳转到通过邮箱注册的页面
            boolean isChinaMain = LanguageUtils.isChinaMain(SplashLoginActivity.this);
            if (isChinaMain)
            {
                LogUtils.d(TAG,"国内, 通过手机号注册");
                Intent intent = new Intent(SplashLoginActivity.this, SignUpByPhoneActivity.class);
                startActivity(intent);
            }else {
                LogUtils.d(TAG,"国外, 通过邮箱注册");
                Intent intent = new Intent(SplashLoginActivity.this, SignUpByEmailForeignActivity.class);
                startActivity(intent);
            }
        }

        public void loginClick() {

            boolean isChinaMain = LanguageUtils.isChinaMain(SplashLoginActivity.this);
            if (isChinaMain)
            {
                LogUtils.d(TAG,"国内, 通过手机号登录");
                Intent intent = new Intent(SplashLoginActivity.this, LoginByPhoneAndCodeActivity.class);
                startActivity(intent);
            }else {
                LogUtils.d(TAG,"国外, 通过邮箱登录");
                Intent intent = new Intent(SplashLoginActivity.this, LoginByAccountForeignActivity.class);
                startActivity(intent);
            }
        }
    }
}
