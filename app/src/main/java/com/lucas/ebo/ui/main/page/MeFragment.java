package com.lucas.ebo.ui.main.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.data.bean.response.DeviceInfoResultBean;
import com.lucas.ebo.ui.activity.add.AddDeviceTipActivity;
import com.lucas.ebo.ui.base.BaseFragment;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.adapter.DeviceListAdapter;
import com.lucas.ebo.ui.main.state.MeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas
 * Date: 2020/5/21 20:31
 * 我的
 */
public class MeFragment extends BaseFragment {

    private MeViewModel mMeViewModel;
    private List<DeviceInfoResultBean> mDeviceInfoList;

    @Override
    protected void initViewModel() {
        mMeViewModel = getFragmentViewModel(MeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_me, mMeViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.adapter, new DeviceListAdapter(getContext()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDeviceInfoList = new ArrayList<>();
        DeviceInfoResultBean d1 = new DeviceInfoResultBean();
        d1.setRobot_name("Robot1");
        mDeviceInfoList.add(d1);
        DeviceInfoResultBean d2 = new DeviceInfoResultBean();
        d2.setRobot_name("Robot2");
        mDeviceInfoList.add(d2);
        mMeViewModel.list.setValue(mDeviceInfoList);
    }

    public class ClickProxy{

        public void add()
        {
            Intent intent = new Intent(getActivity(), AddDeviceTipActivity.class);
            startActivity(intent);
        }

    }
}
