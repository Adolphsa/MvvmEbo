package com.lucas.ebo.ui.activity.account;

import android.os.Bundle;

import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.SignUpByEmailForeignViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/4/28 15:43
 *
 * Description: 注册通过邮箱 国外
 *
 */
public class SignUpByEmailForeignActivity extends BaseActivity {

    private SignUpByEmailForeignViewModel mSignUpByEmailForeignViewModel;

    @Override
    protected void initViewModel() {
        mSignUpByEmailForeignViewModel = getActivityViewModel(SignUpByEmailForeignViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_sign_up_by_email_foreign, mSignUpByEmailForeignViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_by_email_foreign);
    }

    public class ClickProxy
    {
        public void back()
        {
            finish();
        }
    }
}
