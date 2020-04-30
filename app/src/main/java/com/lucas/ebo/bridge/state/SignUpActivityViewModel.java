package com.lucas.ebo.bridge.state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lucas.ebo.data.bean.RegisterResultBean;
import com.lucas.ebo.data.repository.DataRepository;

/**
 * Created by lucas
 * Date: 2020/4/1 18:50
 */
public class SignUpActivityViewModel extends ViewModel {

public MutableLiveData<RegisterResultBean> resultBeanMutableLiveData;


    public MutableLiveData<RegisterResultBean> getResultBeanMutableLiveData() {
        if (resultBeanMutableLiveData == null)
        {
            resultBeanMutableLiveData = new MutableLiveData<>();
        }
        return resultBeanMutableLiveData;
    }

    public void registerByEmail(String email, String pwd1, String pwd2)
    {
        DataRepository.getInstance().registerByEmail(email, pwd1, pwd2, getResultBeanMutableLiveData());
    }

}
