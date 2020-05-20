package com.lucas.ebo.ui.activity.account;

import android.os.Bundle;

import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.RegisterSetPasswordViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 * <p>
 * Date: 2020/4/28 16:41
 * <p>
 * Description: 登录重置密码
 */
public class RegisterSetPasswordActivity extends BaseActivity {

    private RegisterSetPasswordViewModel mRegisterSetPasswordViewModel;

    @Override
    protected void initViewModel() {
        mRegisterSetPasswordViewModel = getActivityViewModel(RegisterSetPasswordViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_register_set_password, mRegisterSetPasswordViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public class ClickProxy {

    }
}
