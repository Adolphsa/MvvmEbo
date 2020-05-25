package com.lucas.ebo.ui.activity.add;

import android.content.Intent;
import android.os.Bundle;

import com.lucas.architecture.utils.StatusBarUtil;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.AddDeviceTipViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/5/25 17:05
 *
 * Description: 添加设备1
 *
 */
public class AddDeviceTipActivity extends BaseActivity {
    
    private AddDeviceTipViewModel mAddDeviceTipViewModel;

    @Override
    protected void initViewModel() {
        mAddDeviceTipViewModel = getActivityViewModel(AddDeviceTipViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_add_device, mAddDeviceTipViewModel)
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
            Intent intent = new Intent(AddDeviceTipActivity.this, ReadyConnectActivity.class);
            startActivity(intent);
        }
    }
}
