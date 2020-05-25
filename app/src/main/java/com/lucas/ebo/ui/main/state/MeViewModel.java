package com.lucas.ebo.ui.main.state;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lucas.ebo.data.bean.response.DeviceInfoResultBean;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/5/22 15:51
 */
public class MeViewModel extends ViewModel {

    public final ObservableBoolean initTabAndPage = new ObservableBoolean();

    public final MutableLiveData<List<DeviceInfoResultBean>> list = new MutableLiveData<>();

    {
        initTabAndPage.set(true);
    }

}
