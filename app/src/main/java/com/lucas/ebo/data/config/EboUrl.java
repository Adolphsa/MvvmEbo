package com.lucas.ebo.data.config;

import rxhttp.wrapper.annotation.DefaultDomain;

import static com.lucas.ebo.BuildConfig.SERVER_URL_CN;

/**
 * Created by lucas
 * Date: 2020/5/18 15:39
 */
public class EboUrl {

    public static final String APP_TYPE = "Android";

    public static final String DOMAIN_TYPE_HTTP = "http://";
    public static final String DOMAIN_TYPE_HTTPS = "https://";

    public static String SERVER_BASE_HOST = SERVER_URL_CN;

    public static final String BASE_USERS_URL = "/api/v1/users/";

    public static final String SERVER_REGISTER_URL = "register/";

    public static final String SERVER_CHECK_PARAMS_URL = "check_params/";

    public static final String SERVER_GET_CODE_URL = "messages/";


    public static String getRequestUrl(String urlType, String urlDetail) {
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(DOMAIN_TYPE_HTTP);
        urlBuffer.append(SERVER_BASE_HOST);
        urlBuffer.append(urlType);
        urlBuffer.append(urlDetail);
        return urlBuffer.toString();
    }

}
