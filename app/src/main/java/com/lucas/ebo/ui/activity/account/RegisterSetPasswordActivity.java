package com.lucas.ebo.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.ToastUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.RegisterSetPasswordViewModel;
import com.lucas.ebo.databinding.ActivityRegisterSetPasswordBinding;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.MainActivity;

import static com.lucas.ebo.utils.ColorConstant.COLOR_ORANGE_96;

/**
 * Created by lucas
 * <p>
 * Date: 2020/4/28 16:41
 * <p>
 * Description: 登录重置密码
 */
public class RegisterSetPasswordActivity extends BaseActivity {

    private static final String TAG = "RegisterSetPasswordActi";

    private RegisterSetPasswordViewModel mRegisterSetPasswordViewModel;

    private ActivityRegisterSetPasswordBinding mRegisterSetPasswordBinding;

    private boolean seeNewPwd = false;
    private boolean seeConfirmPwd = false;


    @Override
    protected void initViewModel() {
        mRegisterSetPasswordViewModel = getActivityViewModel(RegisterSetPasswordViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_register_set_password, mRegisterSetPasswordViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.activity, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegisterSetPasswordBinding = (ActivityRegisterSetPasswordBinding)getBinding();

    }

    public void onConfirmPwdTextChange(String str)
    {

        String newPwd = mRegisterSetPasswordViewModel.newPwd.get();
        LogUtils.d(TAG, "newPwd = " + newPwd);
        LogUtils.d(TAG, "str = " + str);
        if (!TextUtils.isEmpty(newPwd) && !TextUtils.isEmpty(str))
        {
            mRegisterSetPasswordViewModel.continueButtonColor.set(COLOR_ORANGE_96);
            mRegisterSetPasswordViewModel.continueButtonClickable.set(true);
        }
    }



    public class ClickProxy {

        public void back()
        {
            finish();
        }

        public void newPwdImgClick()
        {
            int index = mRegisterSetPasswordBinding.pwdEditPassword.getSelectionStart();
            seeNewPwd = !seeNewPwd;
            if (seeNewPwd)
            {
                mRegisterSetPasswordBinding.pwdEditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mRegisterSetPasswordViewModel.newPwdShowImg.set(getDrawable(R.drawable.icon_pwd_see));
            }else {
                mRegisterSetPasswordBinding.pwdEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mRegisterSetPasswordViewModel.newPwdShowImg.set(getDrawable(R.drawable.icon_pwd_unsee));
            }
            mRegisterSetPasswordBinding.pwdEditPassword.setSelection(index);
        }

        public void confirmPwdImgClick()
        {
            int index = mRegisterSetPasswordBinding.pwdEditConfirmPassword.getSelectionStart();
            seeConfirmPwd = !seeConfirmPwd;
            if (seeConfirmPwd)
            {
                mRegisterSetPasswordBinding.pwdEditConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mRegisterSetPasswordViewModel.confirmShowImg.set(getDrawable(R.drawable.icon_pwd_see));
            }else {
                mRegisterSetPasswordBinding.pwdEditConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mRegisterSetPasswordViewModel.confirmShowImg.set(getDrawable(R.drawable.icon_pwd_unsee));
            }
            mRegisterSetPasswordBinding.pwdEditConfirmPassword.setSelection(index);
        }

        public void continueButtonClick()
        {

            String newPwd = mRegisterSetPasswordViewModel.newPwd.get();
            String confirmPwd = mRegisterSetPasswordViewModel.confirmPwd.get();

            if (TextUtils.isEmpty(newPwd))
            {
                ToastUtils.showShortSafe("新密码为空");
                return;
            }

            if (TextUtils.isEmpty(confirmPwd))
            {
                ToastUtils.showShortSafe("确认密码为空");
                return;
            }

            if (!newPwd.equals(confirmPwd))
            {
                ToastUtils.showShortSafe("两次输入的密码不一致");
                return;
            }

            Intent intent = new Intent(RegisterSetPasswordActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}
