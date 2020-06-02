package com.lucas.ebo.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.lucas.architecture.utils.RegexUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.request.AccountRequestViewModel;
import com.lucas.ebo.bridge.state.SignUpByEmailViewModel;
import com.lucas.ebo.data.bean.request.AuthCodeRequestBean;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.country.CountryPickActivity;

import static com.lucas.ebo.utils.ColorConstant.COLOR_GRAY_D2;
import static com.lucas.ebo.utils.ColorConstant.COLOR_ORANGE_96;

/**
 * Created by lucas
 *
 * Date: 2020/4/28 15:41
 *
 * Description: 国内注册 通过邮箱
 *
 */
public class SignUpByEmailActivity extends BaseActivity {

    private SignUpByEmailViewModel mSignUpByEmailViewModel;
    private AccountRequestViewModel mAccountRequestViewModel;

    @Override
    protected void initViewModel() {
        mSignUpByEmailViewModel = getActivityViewModel(SignUpByEmailViewModel.class);
        mAccountRequestViewModel = getActivityViewModel(AccountRequestViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_sign_up_by_email, mSignUpByEmailViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.activity, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSharedViewModel().countryDisplayName.observe(this, countryDisplayName -> {
            mSignUpByEmailViewModel.countryDisplayName.setValue(countryDisplayName);
        });

        mAccountRequestViewModel.sendCodeBoolean.observe(this, aBoolean -> {
            if (aBoolean)
            {
                dismissLoading();

                String email = mSignUpByEmailViewModel.emailText.get();
                getSharedViewModel().emailAddress.setValue(email);

                Intent intent = new Intent(SignUpByEmailActivity.this, RegisterSetPasswordActivity.class);
                startActivity(intent);
            }else {
                dismissLoading();
            }
        });
    }

    public void onEmailTextChange(String str)
    {
        if (!TextUtils.isEmpty(str)) {
            mSignUpByEmailViewModel.continueButtonClickable.set(true);
            mSignUpByEmailViewModel.continueButtonColor.set(COLOR_ORANGE_96);

        } else {
            mSignUpByEmailViewModel.continueButtonClickable.set(false);
            mSignUpByEmailViewModel.continueButtonColor.set(COLOR_GRAY_D2);

        }
    }

    public class ClickProxy
    {
        public void back()
        {
            finish();
        }

        public void toCountryPick() {
            Intent intent = new Intent(SignUpByEmailActivity.this, CountryPickActivity.class);
            startActivity(intent);
        }

        public void continueClick()
        {
            showLoading();
            String email = mSignUpByEmailViewModel.emailText.get();
            //检查email地址是否合法
            if (TextUtils.isEmpty(email)) {
                SmartToast.showInCenter("Email为空");
                dismissLoading();
                return;
            }
            if (!RegexUtils.isEmail(email)) {
                SmartToast.showInCenter("请输入正确的Email地址");
                dismissLoading();
                return;
            }

            //检查email是否被注册 待处理
            //检查号码是否合法
            AuthCodeRequestBean checkParams = new AuthCodeRequestBean();
            checkParams.setEmail(email);
            mAccountRequestViewModel.requestCheckParams(checkParams);
        }
    }
}
