package com.lucas.ebo.ui.activity.add;

import android.os.Bundle;

import com.lucas.architecture.utils.StatusBarUtil;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.ReadyConnectViewModel;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;

/**
 * Created by lucas
 *
 * Date: 2020/5/25 17:06
 *
 * Description: 添加设备2 Charging device
 *
 */
public class ReadyConnectActivity extends BaseActivity {

    private ReadyConnectViewModel mReadyConnectViewModel;

    @Override
    protected void initViewModel() {
        mReadyConnectViewModel = getActivityViewModel(ReadyConnectViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_ready_connect, mReadyConnectViewModel)
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

        }

    }
}
