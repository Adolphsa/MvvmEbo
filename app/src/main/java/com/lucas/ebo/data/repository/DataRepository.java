package com.lucas.ebo.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.data.api.ServerApi;
import com.lucas.ebo.data.bean.RegisterResultBean;
import com.lucas.ebo.data.repository.source.IHttpDataSource;
import com.lucas.ebo.data.repository.source.ILocalDataSource;

import java.util.HashMap;

import static com.lucas.ebo.data.api.ServerApi.SERVER_REGISTER_URL;
import static com.lucas.ebo.data.api.ServerApi.USERS_URL;

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
    public void registerByEmail(String email, String pwd1, String pwd2, MutableLiveData<RegisterResultBean> liveData) {

        String url = ServerApi.getRequestUrl(USERS_URL, SERVER_REGISTER_URL);
        HashMap<String, String> para = new HashMap<String, String>();
        para.put("email", email);
        para.put("password_1", pwd1);
        para.put("password_2", pwd2);
        para.put("app_kind", "android");
        para.put("language", "zh-hans");

        LogUtils.d(TAG, url);

    }
}
