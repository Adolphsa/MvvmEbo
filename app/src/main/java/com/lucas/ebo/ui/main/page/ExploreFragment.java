package com.lucas.ebo.ui.main.page;

import com.lucas.ebo.R;
import com.lucas.ebo.ui.base.BaseFragment;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.state.ExploreViewModel;

/**
 * Created by lucas
 * Date: 2020/5/21 20:34
 * 视频浏览
 */
public class ExploreFragment extends BaseFragment {

    private ExploreViewModel mExploreViewModel;

    @Override
    protected void initViewModel() {
        mExploreViewModel = getFragmentViewModel(ExploreViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_explore, mExploreViewModel);
    }
}
