package com.lucas.ebo.ui.country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.ThreadUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.databinding.ActivityCountryPickBinding;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.utils.LocaleUtils;

import java.util.List;

/**
 * Created by lucas
 *
 * Date: 2020/4/28 20:49
 *
 * Description: 国家选择
 *
 */
public class CountryPickActivity extends BaseActivity {
    private static final String TAG = "CountryPickActivity";

    private CountryPickViewModel mCountryPickViewModel;
    private MultipleItemQuickAdapter mMultipleItemQuickAdapter;
    private ActivityCountryPickBinding mCountryPickBinding;


    @Override
    protected void initViewModel() {
        mCountryPickViewModel = getActivityViewModel(CountryPickViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_country_pick, mCountryPickViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCountryPickBinding = (ActivityCountryPickBinding)getBinding();

        initCountryData();
    }

    /**
     * 在子线程获取国家数据
     */
    private void initCountryData()
    {
        ThreadUtils.executeByFixed(6, new ThreadUtils.SimpleTask<List<QuickMultipleEntity>>() {
            @Override
            public List<QuickMultipleEntity> doInBackground() throws Throwable {
                return LocaleUtils.getCountryData(CountryPickActivity.this);
            }

            @Override
            public void onSuccess(List<QuickMultipleEntity> result) {
                mMultipleItemQuickAdapter = new MultipleItemQuickAdapter(result);
                mCountryPickBinding.countryRvPick.setAdapter(mMultipleItemQuickAdapter);

            }
        });
    }

    public class ClickProxy
    {
        public void back()
        {
            finish();
        }
    }

}
