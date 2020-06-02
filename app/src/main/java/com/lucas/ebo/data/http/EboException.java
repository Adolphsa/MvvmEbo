package com.lucas.ebo.data.http;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.lucas.ebo.data.callback.EboCallback;
import com.lucas.ebo.data.config.EboCodeRule;

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

    public static void handleFailure(int code, String msg) {
        switch (code) {
            case EboCodeRule.CODE_193106:
                SmartToast.showInCenter("手机号已被注册");
                break;
            case EboCodeRule.CODE_193107:
            case EboCodeRule.CODE_193108:
            case EboCodeRule.CODE_193115:
                SmartToast.showInCenter("消息发送太频繁");
                break;

            case EboCodeRule.CODE_193114:
                SmartToast.showInCenter("验证码已过期");
                break;

            case EboCodeRule.CODE_193112:
                SmartToast.showInCenter("验证码错误");
                break;

            case EboCodeRule.CODE_193119:
                SmartToast.showInCenter("账号已激活");
                break;

            case EboCodeRule.CODE_193109:
                SmartToast.showInCenter("账号未激活");
                break;

            default:
                break;
        }
    }

    public static void handleError() {
        SmartToast.showInCenter("网络异常");
    }
}
