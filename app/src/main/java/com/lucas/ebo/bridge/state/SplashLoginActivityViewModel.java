package com.lucas.ebo.bridge.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.lucas.ebo.R;


/**
 * Created by lucas
 * Date: 2020/4/1 18:50
 */
public class SplashLoginActivityViewModel extends ViewModel {

    public final ObservableField<String> videoAssetPath = new ObservableField<>();

    {
        String assetPath = "android.resource://" + "com.lucas.ebo"+ "/" + R.raw.background;
        videoAssetPath.set(assetPath);
    }

}
