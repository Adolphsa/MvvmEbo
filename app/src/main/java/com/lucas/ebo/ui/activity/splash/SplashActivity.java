package com.lucas.ebo.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.ebo.R;
import com.lucas.ebo.ui.base.BaseActivity;

/**
 * Created by lucas
 *
 * Date: 2020/4/1 18:50
 *
 * Description: 
 *
 */
public class SplashActivity extends BaseActivity {

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
