package com.lucas.ebo.ui.activity.add;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.architecture.utils.StatusBarUtil;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.ShowQRViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/5/25 17:08
 *
 * Description: 添加设备5 Show the QR code
 *
 */
public class ShowQRActivity extends BaseActivity {

    private ShowQRViewModel mShowQRViewModel;

    @Override
    protected void initViewModel() {
        mShowQRViewModel = getActivityViewModel(ShowQRViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_show_q_r, mShowQRViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDarkMode(this);
    }


    public class ClickProxy
    {
        public void back()
        {
            finish();
        }

        public void toNext()
        {
            Intent intent = new Intent(ShowQRActivity.this, ConnectSuccessActivity.class);
            startActivity(intent);
        }
    }
}
