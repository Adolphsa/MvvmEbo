package com.lucas.ebo.bridge.state;

import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.lucas.architecture.utils.Utils;
import com.lucas.ebo.R;
import com.lucas.ebo.utils.ColorConstant;

/**
 * Created by lucas
 * Date: 2020/5/20 19:32
 */
public class RegisterSetPasswordViewModel extends ViewModel {

    public final ObservableField<String> newPwd = new ObservableField<>();

    public final ObservableField<String> confirmPwd = new ObservableField<>();

    public final ObservableField<Drawable> newPwdShowImg = new ObservableField<>();

    public final ObservableField<Drawable> confirmShowImg = new ObservableField<>();

    public final ObservableInt continueButtonColor = new ObservableInt();

    public final ObservableBoolean continueButtonClickable = new ObservableBoolean();

    {
        newPwdShowImg.set(Utils.getApp().getResources().getDrawable(R.drawable.icon_pwd_unsee));
        confirmShowImg.set(Utils.getApp().getResources().getDrawable(R.drawable.icon_pwd_unsee));


        continueButtonClickable.set(false);
        continueButtonColor.set(ColorConstant.COLOR_GRAY_D2);
    }

}
