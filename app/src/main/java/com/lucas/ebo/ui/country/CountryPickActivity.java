package com.lucas.ebo.ui.country;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lucas.architecture.utils.KeyboardUtils;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.ThreadUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.databinding.ActivityCountryPickBinding;
import com.lucas.ebo.generated.callback.OnTextChangedListener;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.view.SideBar;
import com.lucas.ebo.utils.LocaleUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas
 * <p>
 * Date: 2020/4/28 20:49
 * <p>
 * Description: 国家选择
 */
public class CountryPickActivity extends BaseActivity {
    private static final String TAG = "CountryPickActivity";

    private CountryPickViewModel mCountryPickViewModel;
    private MultipleItemQuickAdapter mMultipleItemQuickAdapter;
    private ActivityCountryPickBinding mCountryPickBinding;

    private List<QuickMultipleEntity> mCountryData;
    private List<QuickMultipleEntity> selectedCountries;
    private int lastPosition = -1;
    private String mCountryDisplayName;
    private int mCountryNumber;
    private String mCountrySimpleName;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void initViewModel() {
        mCountryPickViewModel = getActivityViewModel(CountryPickViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_country_pick, mCountryPickViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.activity, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCountryPickBinding = (ActivityCountryPickBinding) getBinding();
        selectedCountries = new ArrayList<>();
        initCountryData();
    }

    private void initView() {
        mMultipleItemQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                LogUtils.d(TAG, "点击了 " + position);

                if (lastPosition != -1) {
                    //clear last item color
                    QuickMultipleEntity lastQuickMultipleEntity = selectedCountries.get(lastPosition);
                    AdapterItemCountry itemCountry = lastQuickMultipleEntity.getItemCountry();
                    if (itemCountry != null) {
                        itemCountry.setSelect(false);
                        mMultipleItemQuickAdapter.setData(lastPosition, lastQuickMultipleEntity);
                    }
                }

                //set current onclick item color
                QuickMultipleEntity currentQuickMultipleEntity = selectedCountries.get(position);
                AdapterItemCountry itemCountry = currentQuickMultipleEntity.getItemCountry();

                if (itemCountry != null) {
                    mCountryDisplayName = itemCountry.getCountryName();
                    mCountryNumber = itemCountry.getCountryNumber();
                    mCountrySimpleName = itemCountry.getCountrySimpleName();

                    itemCountry.setSelect(true);
                    mMultipleItemQuickAdapter.setData(position, currentQuickMultipleEntity);

                    //将done button设置为true
                    mCountryPickViewModel.doneButtonEnable.setValue(true);
                }else {
                    mCountryPickViewModel.doneButtonEnable.setValue(false);
                }
                mMultipleItemQuickAdapter.notifyDataSetChanged();
                lastPosition = position;
            }
        });

        //textVIew done color
        mCountryPickViewModel.doneButtonEnable.observe(this, doneButtonEnable -> {
            if (doneButtonEnable) {
                mCountryPickViewModel.textColor.set(R.color.country_select_color);
            } else {
                mCountryPickViewModel.textColor.set(R.color.country_default_color);
            }
        });

        //side bar
        SideBar sideBar = mCountryPickBinding.countrySideBar;
        sideBar.setOnLetterChangeListener(new SideBar.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {

                mCountryPickViewModel.letterVisible.set(true);
                mCountryPickViewModel.letter.set(letter);
                int letterPosition = getLetterPosition(letter);
                LogUtils.d(TAG, "letter position = " + letterPosition);
                if (letterPosition != -1)
                {
                    mLinearLayoutManager.scrollToPositionWithOffset(letterPosition, 0);
                }
            }

            @Override
            public void onReset() {
                mCountryPickViewModel.letterVisible.set(false);
            }
        });

    }

    /**
     * get letter position
     * @param letter letter
     * @return position
     */
    private int getLetterPosition(String letter)
    {
        for (QuickMultipleEntity quickMultipleEntity : mCountryData) {
            AdapterItemLetter itemLetter = quickMultipleEntity.getItemLetter();
            if (itemLetter != null)
            {
                String s = itemLetter.getLetter();
                if (!TextUtils.isEmpty(s) && s.equalsIgnoreCase(letter))
                {
                    return mCountryData.indexOf(quickMultipleEntity);
                }
            }
        }
        return -1;
    }

    /**
     * 国家搜索
     * @param str str
     */
    public void onTextChanged(String str)
    {
        LogUtils.d(TAG, "输入的 = " + str);
        selectedCountries.clear();

        if (TextUtils.isEmpty(str))
        {
            LogUtils.d(TAG, "输入的为空 size = " + mCountryData.size());
            selectedCountries.addAll(mCountryData);
            mMultipleItemQuickAdapter.setDiffNewData(selectedCountries);
            if (KeyboardUtils.isSoftInputVisible(CountryPickActivity.this))
            {
                //hide keyboard
                KeyboardUtils.hideSoftInput(CountryPickActivity.this);
            }

            mCountryPickViewModel.sideBarVisible.set(true);

        }else {
            for (QuickMultipleEntity quickMultipleEntity : mCountryData) {
                AdapterItemCountry itemCountry = quickMultipleEntity.getItemCountry();
                if (itemCountry != null)
                {
                    if (itemCountry.getCountryName().toLowerCase().contains(str.toLowerCase()))
                    {
                        selectedCountries.add(quickMultipleEntity);
                    }
                }

            }
            LogUtils.d(TAG, "selectedCountries size = " + selectedCountries.size());
            mMultipleItemQuickAdapter.setDiffNewData(selectedCountries);
            mCountryPickViewModel.sideBarVisible.set(false);
        }

        mMultipleItemQuickAdapter.notifyDataSetChanged();

    }

    /**
     * 在子线程获取国家数据
     */
    private void initCountryData() {
        ThreadUtils.executeByFixed(3, new ThreadUtils.SimpleTask<List<QuickMultipleEntity>>() {
            @Override
            public List<QuickMultipleEntity> doInBackground() throws Throwable {
                mCountryData = LocaleUtils.getCountryData(CountryPickActivity.this);
                selectedCountries.addAll(mCountryData);
                return selectedCountries;
            }

            @Override
            public void onSuccess(List<QuickMultipleEntity> result) {
                mMultipleItemQuickAdapter = new MultipleItemQuickAdapter(result);
                mMultipleItemQuickAdapter.setDiffCallback(new RecycleViewDiffCallback());

                mLinearLayoutManager = new LinearLayoutManager(CountryPickActivity.this);
                mCountryPickBinding.countryRvPick.setLayoutManager(mLinearLayoutManager);
                mCountryPickBinding.countryRvPick.setAdapter(mMultipleItemQuickAdapter);

                initView();
            }
        });
    }

    public class ClickProxy {

        public void back() {
            finish();
        }

        public void textDone() {
            LogUtils.d(TAG, "国家 = " + mCountryDisplayName + ",国家号 = " + mCountryNumber + "国家简称 = " + mCountrySimpleName);
            getSharedViewModel().countryDisplayName.setValue(mCountryDisplayName);
            getSharedViewModel().countryNumber.setValue(mCountryNumber);
            finish();
        }
    }

}
