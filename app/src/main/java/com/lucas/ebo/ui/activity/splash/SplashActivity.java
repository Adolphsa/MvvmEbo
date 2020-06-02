package com.lucas.ebo.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.ebo.data.repository.DataRepository;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.MainActivity;

/**
 * Created by lucas
 *
 * Date: 2020/4/1 18:50
 *
 * Description: 闪屏页面
 *
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void initViewModel() {

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

//        toSplashLoginActivity();

        if (DataRepository.getInstance().getLoginStatus())
        {
            //已经登录
            toMainActivity();
        }else {
            toSplashLoginActivity();
        }

    }

    private void toSplashLoginActivity()
    {
        Intent intent = new Intent(this, SplashLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void toMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
