package com.lucas.ebo.ui.activity.add;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.architecture.utils.StatusBarUtil;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.AddWifiViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/5/25 17:07
 *
 * Description:添加设备3 Connect wifi network
 *
 */
public class AddWifiActivity extends BaseActivity {

    private AddWifiViewModel mAddWifiViewModel;

    @Override
    protected void initViewModel() {
        mAddWifiViewModel = getActivityViewModel(AddWifiViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_add_wifi, mAddWifiViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDarkMode(this);
    }


    public class ClickProxy{

        public void back()
        {
            finish();
        }

        public void toNext()
        {
            Intent intent = new Intent(AddWifiActivity.this, ScanQRActivity.class);
            startActivity(intent);
        }
    }
}
