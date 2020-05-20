package com.lucas.ebo.data.http;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.ToastUtils;
import com.lucas.ebo.data.callback.EboCallback;
import com.lucas.ebo.data.config.EboCodeRule;
import com.lucas.ebo.data.parser.ResponseParser;

import java.io.IOException;

/**
 * Created by lucas
 * Date: 2020/5/19 17:16
 */
public class EboException extends IOException {

    private static final String TAG = "EboException";

    private EboCallback mEboCallback;

    private int code;

    private String msg;

    public EboException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static void handleFailure(int code, String msg)
    {
        switch (code) {
            case EboCodeRule.CODE_193106:
                ToastUtils.showShortSafe("手机号已被注册");
                break;
            case EboCodeRule.CODE_193107:
            case EboCodeRule.CODE_193108:
            case EboCodeRule.CODE_193115:
                ToastUtils.showShortSafe("消息发送太频繁");
                break;

            case EboCodeRule.CODE_193114:
                ToastUtils.showShortSafe("验证码已过期");
                break;

            case EboCodeRule.CODE_193112:
                ToastUtils.showShortSafe("验证码错误");
                break;
            default:
                break;
        }
    }

}
