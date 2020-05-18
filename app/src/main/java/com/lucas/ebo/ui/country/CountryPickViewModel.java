package com.lucas.ebo.ui.country;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lucas.ebo.R;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/4/29 10:50
 */
public class CountryPickViewModel extends ViewModel {

    public final MutableLiveData<Boolean> doneButtonEnable = new MutableLiveData<>();

    public final ObservableInt textColor = new ObservableInt();

    public final ObservableBoolean letterVisible = new ObservableBoolean();

    public final ObservableField<String> letter = new ObservableField<>();

    public final ObservableBoolean sideBarVisible = new ObservableBoolean();

    public final MutableLiveData<List<QuickMultipleEntity>> countryList = new MutableLiveData<>();

    {
        doneButtonEnable.setValue(false);
        textColor.set(R.color.country_default_color);
        letterVisible.set(false);
        sideBarVisible.set(true);
    }
}
