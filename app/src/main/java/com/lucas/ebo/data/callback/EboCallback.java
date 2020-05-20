package com.lucas.ebo.data.callback;

/**
 * Created by lucas
 * Date: 2020/4/8 14:31
 */
public interface EboCallback {

    void onSuccess(String result);

    void onError(String error, int code);

    void onException(String exception);


}
