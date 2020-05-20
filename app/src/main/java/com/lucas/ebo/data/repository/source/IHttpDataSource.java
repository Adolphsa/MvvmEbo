/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lucas.ebo.data.repository.source;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.lucas.ebo.data.bean.request.AuthCodeRequestBean;
import com.lucas.ebo.data.bean.request.RegisterRequestBean;
import com.lucas.ebo.data.bean.respone.RegisterResultBean;

/**
 * Create by KunMinX at 19/10/29
 */
public interface IHttpDataSource {

    void registerByPhone(RegisterRequestBean registerRequestBean, MutableLiveData<RegisterResultBean> liveData);

    void registerByEmail(String email,  String pwd1, String pwd2, MutableLiveData<RegisterResultBean> liveData);

    void checkParam(AuthCodeRequestBean authCodeRequestBean, MutableLiveData<Boolean> sendCodeBoolean);

    void getAuthCode(AuthCodeRequestBean authCodeRequestBean);

}
