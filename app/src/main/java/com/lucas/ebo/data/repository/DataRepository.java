package com.lucas.ebo.data.repository;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;
import com.lucas.architecture.utils.GsonUtils;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.ThreadUtils;
import com.lucas.ebo.data.bean.request.AuthCodeRequestBean;
import com.lucas.ebo.data.bean.request.RegisterRequestBean;
import com.lucas.ebo.data.bean.response.RegisterResultBean;
import com.lucas.ebo.data.callback.EboCallback;
import com.lucas.ebo.data.config.EboCodeRule;
import com.lucas.ebo.data.config.EboUrl;
import com.lucas.ebo.data.http.HttpManager;
import com.lucas.ebo.data.repository.database.EboRoomDatabase;
import com.lucas.ebo.data.repository.database.UserInfo;
import com.lucas.ebo.data.repository.source.IHttpDataSource;
import com.lucas.ebo.data.repository.source.ILocalDataSource;
import com.lucas.ebo.ui.base.BaseResponse;
import com.tencent.mmkv.MMKV;

import java.lang.reflect.Type;
import java.util.HashMap;

import static com.lucas.ebo.data.config.EboUrl.APP_TYPE;
import static com.lucas.ebo.data.config.EboUrl.BASE_USERS_URL;
import static com.lucas.ebo.data.config.EboUrl.SERVER_ACTIVE_AGAIN_URL;
import static com.lucas.ebo.data.config.EboUrl.SERVER_CHECK_PARAMS_URL;
import static com.lucas.ebo.data.config.EboUrl.SERVER_GET_CODE_URL;
import static com.lucas.ebo.data.config.EboUrl.SERVER_REGISTER_URL;

/**
 * Created by lucas
 * Date: 2020/4/6 16:25
 */
public class DataRepository implements IHttpDataSource, ILocalDataSource {

    private static final String TAG = "DataRepository";

    private static MMKV mmkv = MMKV.defaultMMKV();

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
        HashMap<String, String> para = new HashMap<String, String>();
        para.put("app_token", registerRequestBean.getApp_token());
        para.put("device_id", registerRequestBean.getDevice_id());
        para.put("app_kind", APP_TYPE);
        para.put("registry_region", registerRequestBean.getRegistry_region());
        para.put("language", registerRequestBean.getLanguage());
        para.put("password_1", registerRequestBean.getPassword_1());
        para.put("password_2", registerRequestBean.getPassword_2());
        para.put("phone_message", registerRequestBean.getPhone_message());
        String email = registerRequestBean.getEmail();
        if (TextUtils.isEmpty(email)) {
            para.put("phone_num", registerRequestBean.getPhone_num());
            para.put("phone_area", registerRequestBean.getPhone_area());
        } else {
            para.put("email", email);
        }

        HttpManager.getInstance().post(url, para, new EboCallback() {
            @Override
            public void onSuccess(String result) {
                Type jsonType = new TypeToken<BaseResponse<RegisterResultBean>>() {
                }.getType();
                BaseResponse<RegisterResultBean> baseResponse = GsonUtils.fromJson(result, jsonType);
                if (baseResponse != null) {
                    RegisterResultBean registerResultBean = baseResponse.getData();
                    int userId = registerResultBean.getUser_id();
                    LogUtils.d(TAG, registerResultBean.toString());
                    //保存UserInfo数据
                    saveUserInfo(registerResultBean);
                    //保存登录状态
                    saveLoginStatus(true);
                    //保存userId
                    saveUserId(userId);
                    liveData.setValue(registerResultBean);
                }
            }

            @Override
            public void onError(String error, int code) {
                saveLoginStatus(false);
            }

            @Override
            public void onException(String exception) {
                saveLoginStatus(false);
            }
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
        if (!TextUtils.isEmpty(phoneNum)) {
            para.put("phone_num", phoneNum);
            para.put("phone_area", authCodeRequestBean.getPhone_area());
        } else if (!TextUtils.isEmpty(email)) {
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

    @Override
    public void checkEmailIsActive(String check, MutableLiveData<Boolean> emailIsActive) {
        String url = EboUrl.getRequestUrl(BASE_USERS_URL, SERVER_ACTIVE_AGAIN_URL);
        HashMap<String, String> para = new HashMap<String, String>();
        para.put("language", "zh-hans");
        if (!TextUtils.isEmpty(check) && check.equals("1")) {
            //当只是确认账号是否已激活,传递该请求参数(否则不需要传递)
            para.put("check", check);
        }
        HttpManager.getInstance().get(url, para, new EboCallback() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String error, int code) {
                if (code == EboCodeRule.CODE_193119) {
                    emailIsActive.setValue(true);
                } else if (code == EboCodeRule.CODE_193109) {
                    emailIsActive.setValue(false);
                }
            }

            @Override
            public void onException(String exception) {
                emailIsActive.setValue(false);
            }
        });
    }

    ///////////////////////////local data/////////////////////////////////////

    @Override
    public void saveLoginStatus(boolean loginStatus) {
        mmkv.encode("is_login", loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return mmkv.decodeBool("is_login");
    }

    @Override
    public void saveUserId(int userId) {
        mmkv.encode("ebo_user_id", userId);
    }

    @Override
    public int getUserId() {
        return mmkv.decodeInt("ebo_user_id");
    }

    @Override
    public void saveUserInfo(RegisterResultBean registerResultBean) {

        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Long>() {
            @Override
            public Long doInBackground() throws Throwable {
                UserInfo userInfo = new UserInfo();
                int userId = registerResultBean.getUser_id();
                userInfo.setUserId(userId);
                userInfo.setUserName(registerResultBean.getUsername());
                userInfo.setPhoneNum(registerResultBean.getPhone_num());
                userInfo.setEmail(registerResultBean.getEmail());
                userInfo.setNickName(registerResultBean.getNickname());
                userInfo.setAvatar(registerResultBean.getAvatar());
                userInfo.setPhoneArea(registerResultBean.getPhone_area());
                userInfo.setValid(registerResultBean.isIs_valid());
                userInfo.setGender(registerResultBean.getGender());
                userInfo.setChangedPwd(registerResultBean.isChanged_pwd());
                userInfo.setThirdParty(registerResultBean.getThird_party());
                userInfo.setAcceptEmail(registerResultBean.isAccept_email());
                return EboRoomDatabase.getInstance().userInfoDao().insertUserInfo(userInfo);
            }

            @Override
            public void onSuccess(Long result) {
                LogUtils.d(TAG, "插入成功userId = " + result);
            }
        });
    }

}
