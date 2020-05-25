package com.lucas.ebo.ui.main.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.lucas.architecture.ui.adapter.SimpleBaseBindingAdapter;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.R;
import com.lucas.ebo.data.bean.response.DeviceInfoResultBean;
import com.lucas.ebo.databinding.AdapterDeviceItemBinding;

/**
 * Created by lucas
 * Date: 2020/5/25 16:17
 */
public class DeviceListAdapter extends SimpleBaseBindingAdapter <DeviceInfoResultBean, AdapterDeviceItemBinding>{

    private static final String TAG = "DeviceListAdapter";

    public DeviceListAdapter(Context context)
    {
        super(context, R.layout.adapter_device_item);
    }

    @Override
    protected void onSimpleBindItem(AdapterDeviceItemBinding binding, DeviceInfoResultBean item, RecyclerView.ViewHolder holder)
    {
        binding.setDevice(item);
        binding.imgBtnDeviceSetting.setOnClickListener(v -> {
            LogUtils.d(TAG, "点击了三个点");
        });
    }
}
