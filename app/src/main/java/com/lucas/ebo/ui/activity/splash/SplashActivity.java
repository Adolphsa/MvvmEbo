package com.lucas.ebo.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lucas.ebo.R;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

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

        toSplashActivity();
    }

    private void toSplashActivity()
    {
        Intent intent = new Intent(this, SplashLoginActivity.class);
        startActivity(intent);
        finish();
    }
}
