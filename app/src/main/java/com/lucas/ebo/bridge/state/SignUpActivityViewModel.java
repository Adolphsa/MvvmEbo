package com.lucas.ebo.bridge.state;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lucas.ebo.R;
import com.lucas.ebo.data.bean.response.RegisterResultBean;
import com.lucas.ebo.data.repository.DataRepository;

import static com.lucas.ebo.utils.ColorConstant.COLOR_GRAY_D2;

/**
 * Created by lucas
 * Date: 2020/4/1 18:50
 */
public class SignUpActivityViewModel extends ViewModel {

    public MutableLiveData<RegisterResultBean> resultBeanMutableLiveData;

    public MutableLiveData<String> countryDisplayName = new MutableLiveData<>();
    public MutableLiveData<String> countryNumber = new MutableLiveData<>();

    public final ObservableField<String> phoneNumber = new ObservableField<>();

    public final ObservableField<String> authCode = new ObservableField<>();

    public final ObservableField<String> sendCode = new ObservableField<>();
    public final ObservableBoolean sendCodeClickable = new ObservableBoolean();
    public final ObservableInt sendCodeTextColor = new ObservableInt();

    public final ObservableInt continueButtonColor = new ObservableInt();
    public final ObservableBoolean continueButtonClickable = new ObservableBoolean();
    public final ObservableField<String> continueButtonText = new ObservableField<>();




    public MutableLiveData<RegisterResultBean> getResultBeanMutableLiveData() {
        if (resultBeanMutableLiveData == null) {
            resultBeanMutableLiveData = new MutableLiveData<>();
        }
        return resultBeanMutableLiveData;
    }

    public void registerByEmail(String email, String pwd1, String pwd2) {
        DataRepository.getInstance().registerByEmail(email, pwd1, pwd2, getResultBeanMutableLiveData());
    }

    {
        countryDisplayName.setValue("China mainland");
        countryNumber.setValue("+86");

        sendCode.set("Send Code");
        sendCodeClickable.set(true);
        sendCodeTextColor.set(R.color.auth_code_color);

        continueButtonClickable.set(false);
        continueButtonColor.set(COLOR_GRAY_D2);
        continueButtonText.set("Sign Up/Login");
    }

}
