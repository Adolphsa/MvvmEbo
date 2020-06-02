package com.lucas.ebo.ui.activity.add;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.architecture.utils.StatusBarUtil;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.ScanQRViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/5/25 17:08
 *
 * Description: 添加设备4 Scan the QR code
 *
 */
public class ScanQRActivity extends BaseActivity {

    private ScanQRViewModel mScanQRViewModel;

    @Override
    protected void initViewModel() {
        mScanQRViewModel = getActivityViewModel(ScanQRViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_scan_q_r, mScanQRViewModel)
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
            Intent intent = new Intent(ScanQRActivity.this, ShowQRActivity.class);
            startActivity(intent);
        }
    }
}
