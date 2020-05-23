package com.lucas.ebo.ui.main.page;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lucas.ebo.R;
import com.lucas.ebo.ui.base.BaseFragment;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.state.MeViewModel;

/**
 * Created by lucas
 * Date: 2020/5/21 20:31
 * 我的
 */
public class MeFragment extends BaseFragment {

    private MeViewModel mMeViewModel;

    @Override
    protected void initViewModel() {
        mMeViewModel = getFragmentViewModel(MeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_me, mMeViewModel);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        BarUtils.setStatusBarColor((AppCompatActivity) getActivity(), Utils.getApp().getResources().getColor(R.color.black));
    }
}
