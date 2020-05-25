package com.lucas.ebo.data.repository;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.lucas.architecture.utils.GsonUtils;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.data.bean.request.AuthCodeRequestBean;
import com.lucas.ebo.data.bean.request.RegisterRequestBean;
import com.lucas.ebo.data.bean.response.RegisterResultBean;
import com.lucas.ebo.data.callback.EboCallback;
import com.lucas.ebo.data.config.EboUrl;
import com.lucas.ebo.data.http.HttpManager;
import com.lucas.ebo.data.repository.source.IHttpDataSource;
import com.lucas.ebo.data.repository.source.ILocalDataSource;

import java.util.HashMap;

import rxhttp.wrapper.param.RxHttp;

import static com.lucas.ebo.data.config.EboUrl.BASE_USERS_URL;
import static com.lucas.ebo.data.config.EboUrl.SERVER_CHECK_PARAMS_URL;
import static com.lucas.ebo.data.config.EboUrl.SERVER_GET_CODE_URL;
import static com.lucas.ebo.data.config.EboUrl.SERVER_REGISTER_URL;

/**
 * Created by lucas
 * Date: 2020/4/6 16:25
 */
public class DataRepository implements IHttpDataSource, ILocalDataSource {

    private static final String TAG = "DataRepository";

    private static final DataRepository S_REQUEST_MANAGER = new DataRepository();
    private MutableLiveData<String> responseCodeLiveData;


    private DataRepository() {
    }


    public static DataRepository getInstance() {
        return S_REQUEST_MANAGER;
    }

    public MutableLiveData<String> getResponseCodeLiveData() {
        if (responseCodeLiveData == null) {
            responseCodeLiveData = new MutableLiveData<>();
        }
        return responseCodeLiveData;
    }


    @Override
    public void registerByPhone(RegisterRequestBean registerRequestBean, MutableLiveData<RegisterResultBean> liveData) {

        String url = EboUrl.getRequestUrl(BASE_USERS_URL, SERVER_REGISTER_URL);
        RxHttp.postJson(url)
                .addAll(GsonUtils.toJson(registerRequestBean))
                .asResponse(RegisterResultBean.class)
                .subscribe(registerResultBean -> {
                    //请求成功
                    liveData.setValue(registerResultBean);
                }, throwable -> {
                    //请求失败
                });

    }

    @Override
    public void registerByEmail(String email, String pwd1, String pwd2, MutableLiveData<RegisterResultBean> liveData) {

        String url = EboUrl.getRequestUrl(BASE_USERS_URL, SERVER_REGISTER_URL);
        HashMap<String, String> para = new HashMap<String, String>();
        para.put("email", email);
        para.put("password_1", pwd1);
        para.put("password_2", pwd2);
        para.put("app_kind", "android");
        para.put("language", "zh-hans");
        LogUtils.d(TAG, url);

    }

    @Override
    public void checkParam(AuthCodeRequestBean authCodeRequestBean, MutableLiveData<Boolean> sendCodeBoolean) {
        String url = EboUrl.getRequestUrl(BASE_USERS_URL, SERVER_CHECK_PARAMS_URL);
        HashMap<String, String> para = new HashMap<String, String>();
        String phoneNum = authCodeRequestBean.getPhone_num();
        String email = authCodeRequestBean.getEmail();
        if (!TextUtils.isEmpty(phoneNum))
        {
            para.put("phone_num", phoneNum);
            para.put("phone_area", authCodeRequestBean.getPhone_area());
        } else if (!TextUtils.isEmpty(email))
        {
            para.put("email", email);
        }

        HttpManager.getInstance().post(url, para, new EboCallback() {
            @Override
            public void onSuccess(String result) {
                sendCodeBoolean.setValue(true);
            }

            @Override
            public void onError(String error, int code) {
                sendCodeBoolean.setValue(false);
            }

            @Override
            public void onException(String exception) {
                sendCodeBoolean.setValue(false);
            }
        });
    }


    @Override
    public void getAuthCode(AuthCodeRequestBean authCodeRequestBean) {

        String url = EboUrl.getRequestUrl(BASE_USERS_URL, SERVER_GET_CODE_URL);
        HashMap<String, String> para = new HashMap<String, String>();
        para.put("language", authCodeRequestBean.getLanguage());
        para.put("phone_num", authCodeRequestBean.getPhone_num());
        para.put("operation", authCodeRequestBean.getOperation());
        para.put("phone_area", authCodeRequestBean.getPhone_area());

        HttpManager.getInstance().get(url, para, new EboCallback() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String error, int code) {

            }

            @Override
            public void onException(String exception) {

            }
        });

    }
}
