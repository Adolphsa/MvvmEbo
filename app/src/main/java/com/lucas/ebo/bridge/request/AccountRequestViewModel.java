package com.lucas.ebo.bridge.request;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lucas.ebo.data.bean.request.AuthCodeRequestBean;
import com.lucas.ebo.data.bean.request.RegisterRequestBean;
import com.lucas.ebo.data.bean.respone.RegisterResultBean;
import com.lucas.ebo.data.repository.DataRepository;

/**
 * Created by lucas
 * Date: 2020/5/19 15:45
 */
public class AccountRequestViewModel extends ViewModel {

    private MutableLiveData<RegisterResultBean> mRegisterResultBeanLiveData;

    public MutableLiveData<Boolean> sendCodeBoolean = new MutableLiveData<>();

    public LiveData<RegisterResultBean> getRegisterResultBeanLiveData()
    {
        if (mRegisterResultBeanLiveData == null)
        {
            mRegisterResultBeanLiveData = new MutableLiveData<>();
        }

        return mRegisterResultBeanLiveData;
    }


    public void requestRegisterByPhone(RegisterRequestBean registerRequestBean)
    {
        DataRepository.getInstance().registerByPhone(registerRequestBean, mRegisterResultBeanLiveData);
    }

    public void requestCheckParams(AuthCodeRequestBean checkParams)
    {
        DataRepository.getInstance().checkParam(checkParams, sendCodeBoolean);
    }

    public void requestGetAuthCode(AuthCodeRequestBean authCodeRequestBean)
    {
        DataRepository.getInstance().getAuthCode(authCodeRequestBean);
    }

}
