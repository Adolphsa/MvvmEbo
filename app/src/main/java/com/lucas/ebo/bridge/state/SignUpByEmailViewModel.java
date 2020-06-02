package com.lucas.ebo.bridge.state;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import static com.lucas.ebo.utils.ColorConstant.COLOR_GRAY_D2;

/**
 * Created by lucas
 * Date: 2020/5/27 15:53
 */
public class SignUpByEmailViewModel extends ViewModel {

    public MutableLiveData<String> countryDisplayName = new MutableLiveData<>();

    public final ObservableField<String> emailText = new ObservableField<>();

    public final ObservableInt continueButtonColor = new ObservableInt();
    public final ObservableBoolean continueButtonClickable = new ObservableBoolean();

    {
        countryDisplayName.setValue("China mainland");

        continueButtonClickable.set(false);
        continueButtonColor.set(COLOR_GRAY_D2);
    }
}
