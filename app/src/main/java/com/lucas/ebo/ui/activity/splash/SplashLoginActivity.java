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
                //信息回调
                //                what 对应返回的值如下
                //                public static final int MEDIA_INFO_UNKNOWN = 1;  媒体信息未知
                //                public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700; 媒体信息视频跟踪滞后
                //                public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3; 媒体信息\视频渲染\开始
                //                public static final int MEDIA_INFO_BUFFERING_START = 701; 媒体信息缓冲启动
                //                public static final int MEDIA_INFO_BUFFERING_END = 702; 媒体信息缓冲结束
                //                public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703; 媒体信息网络带宽（703）
                //                public static final int MEDIA_INFO_BAD_INTERLEAVING = 800; 媒体-信息-坏-交错
                //                public static final int MEDIA_INFO_NOT_SEEKABLE = 801; 媒体信息找不到
                //                public static final int MEDIA_INFO_METADATA_UPDATE = 802; 媒体信息元数据更新
                //                public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901; 媒体信息不支持字幕
                //                public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902; 媒体信息字幕超时
                //如果方法处理了信息，则为true；如果没有，则为false。返回false或根本没有OnInfoListener，将导致丢弃该信息。
                return false;
            }
        });
    }

    public class ClickProxy {

        public void guestClick() {

        }

        public void signUpClick() {
            Log.i(TAG, "signUpClick: ");
            Intent intent = new Intent(SplashLoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }

        public void loginClick() {
            Log.i(TAG, "loginClick: ");
            Intent intent = new Intent(SplashLoginActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
