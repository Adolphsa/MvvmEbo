package com.lucas.ebo.ui.activity.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import androidx.databinding.DataBindingUtil;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.SplashLoginActivityViewModel;
import com.lucas.ebo.databinding.ActivitySplashLoginBinding;
import com.lucas.ebo.ui.activity.accout.LoginActivity;
import com.lucas.ebo.ui.activity.accout.SignUpActivity;
import com.lucas.ebo.ui.base.BaseActivity;

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

    private ActivitySplashLoginBinding mBinding;
    private SplashLoginActivityViewModel mSplashLoginActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashLoginActivityViewModel = getActivityViewModelProvider(this).get(SplashLoginActivityViewModel.class);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash_login);
        mBinding.setLifecycleOwner(this);
        mBinding.setClick(new ClickProxy());
        mBinding.setVm(mSplashLoginActivityViewModel);

        initAnimation();
        initVideoView();
    }

    private void initAnimation()
    {
        //申明一个动画
        Animation ani= AnimationUtils.loadAnimation(this,R.anim.logo_toming);
        //为空间绑定动画
        mBinding.splashBtnGuest.setAnimation(ani);
        mBinding.splashImageLogo.setAnimation(ani);
        mBinding.splashBottomLl.setVisibility(View.GONE);
        mBinding.splashBottomLl.postDelayed(new Runnable() {
            @Override
            public void run() {
                //-1.0表示控件初始值
                TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mHiddenAction.setDuration(2000);
                mBinding.splashBottomLl.startAnimation(mHiddenAction);
                mBinding.splashBottomLl.setVisibility(View.VISIBLE);
            }
        },2000);
    }

    private void initVideoView(){
//        String assetPath="file:///android_asset/background.mp4";
        String assetPath="android.resource://" + getPackageName() + "/" + R.raw.background;
        //设置视频文件
        mBinding.splashVideo.setVideoPath(assetPath);
        mBinding.splashVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //视频加载完成,准备好播放视频的回调
                mBinding.splashVideo.start();
            }
        });
        mBinding.splashVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
        mBinding.splashVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                ////如果方法处理了错误，则为true；否则为false。返回false或根本没有OnErrorListener，将导致调用OnCompletionListener。
                return false;
            }
        });
        mBinding.splashVideo.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }

    public class ClickProxy {

        public void guestClick() {

        }

        public void signUpClick() {
            LogUtils.dTag(TAG, "点击signUpClick");
            Intent intent = new Intent(SplashLoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }

        public void loginClick() {
            LogUtils.dTag(TAG, "loginClick");
            Intent intent = new Intent(SplashLoginActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
