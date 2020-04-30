package com.lucas.ebo.ui.country;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/4/29 10:50
 */
public class CountryPickViewModel extends ViewModel {

    public final MutableLiveData<List<QuickMultipleEntity>> countryList = new MutableLiveData<>();


}
