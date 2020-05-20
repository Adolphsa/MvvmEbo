package com.lucas.ebo.data.http;

import com.lucas.architecture.utils.GsonUtils;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.NetworkUtils;
import com.lucas.architecture.utils.ThreadUtils;
import com.lucas.architecture.utils.ToastUtils;
import com.lucas.architecture.utils.Utils;
import com.lucas.ebo.data.callback.EboCallback;
import com.lucas.ebo.data.config.EboCodeRule;
import com.lucas.ebo.ui.base.BaseResponse;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.X509TrustManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.ssl.X509TrustManagerImpl;

import static com.lucas.ebo.BuildConfig.DEBUG;

/**
 * Created by lucas
 * Date: 2020/5/18 16:14
 */
public class HttpManager {

    private static final String TAG = "HttpManager";

    private HttpManager() {
    }

    public static synchronized HttpManager getInstance() {
        return HttpManagerHolder.instance;
    }

    public static class HttpManagerHolder {
        private static final HttpManager instance = new HttpManager();
    }

    public void init() {
        RxHttp.setDebug(DEBUG);
        RxHttp.init(getDefaultOkHttpClient());
    }

    private OkHttpClient getDefaultOkHttpClient() {

        X509TrustManager trustAllCert = new X509TrustManagerImpl();

        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                //添加信任证书
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), trustAllCert)
                //忽略host验证
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
    }

    /**
     * 处理成功回调
     *
     * @param result         返回结果
     * @param commonCallback callback
     */
    private void handleSuccess(String result, EboCallback commonCallback) {
        LogUtils.d(TAG, result);
        if (commonCallback != null) {
            BaseResponse baseResponse = GsonUtils.fromJson(result, BaseResponse.class);
            if (baseResponse != null) {
                int code = baseResponse.getCode();
                String msg = baseResponse.getMsg();

                if (EboCodeRule.CODE_193100 == code || EboCodeRule.CODE_194400 == code) {
                    commonCallback.onSuccess(result);
                } else {
                    commonCallback.onError(msg, code);
                    EboException.handleFailure(code, msg);
                }
            }
        }
    }

    /**
     * 处理失败回调
     *
     * @param throwable      throwable
     * @param commonCallback callback
     */
    private void handleException(Throwable throwable, EboCallback commonCallback) {
        LogUtils.d(TAG, "请求失败 " + throwable.getMessage());
        if (commonCallback != null) {
            commonCallback.onException(throwable.getMessage());
        }
    }

    /**
     * rxhttp get
     *
     * @param url            url
     * @param paramMap       paramMap
     * @param commonCallback commonCallback
     */
    public void get(String url, HashMap<String, String> paramMap, EboCallback commonCallback) {

        //首先检查网络
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShortSafe("请检查网络");
            return;
        }
        RxHttp.get(url)
                .addAll(paramMap)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    //成功回调 s为String类型，主线程回调
                    handleSuccess(s, commonCallback);
                }, throwable -> {
                    //失败回调
                    handleException(throwable, commonCallback);
                });
    }


    /**
     * rxhttp post
     *
     * @param url            url
     * @param paramMap       params
     * @param commonCallback callback
     */
    public void post(String url, HashMap<String, String> paramMap, EboCallback commonCallback) {

        //首先检查网络
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShortSafe("请检查网络");
            return;
        }

        RxHttp.postForm(url)
                .addAll(paramMap)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    //成功回调 s为String类型，主线程回调
                    handleSuccess(s, commonCallback);
                }, throwable -> {
                    //失败回调
                    handleException(throwable, commonCallback);
                });
    }


    /**
     * rxhttp put
     *
     * @param url            url
     * @param paramMap       params
     * @param commonCallback callback
     */
    public void put(String url, HashMap<String, String> paramMap, EboCallback commonCallback) {

        //首先检查网络
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShortSafe("请检查网络");
            return;
        }

        RxHttp.putForm(url)
                .addAll(paramMap)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    //成功回调 s为String类型，主线程回调
                    handleSuccess(s, commonCallback);
                }, throwable -> {
                    //失败回调
                    handleException(throwable, commonCallback);
                });
    }

    /**
     * rxhttp delete
     *
     * @param url            url
     * @param paramMap       params
     * @param commonCallback callback
     */
    public void delete(String url, HashMap<String, String> paramMap, EboCallback commonCallback) {

        //首先检查网络
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShortSafe("请检查网络");
            return;
        }

        RxHttp.deleteForm(url)
                .addAll(paramMap)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    //成功回调 s为String类型，主线程回调
                    handleSuccess(s, commonCallback);
                }, throwable -> {
                    //失败回调
                    handleException(throwable, commonCallback);
                });
    }
}
