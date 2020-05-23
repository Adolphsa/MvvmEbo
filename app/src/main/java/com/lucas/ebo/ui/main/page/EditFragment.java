package com.lucas.ebo.ui.main.page;

import androidx.fragment.app.Fragment;

import com.lucas.ebo.R;
import com.lucas.ebo.ui.base.BaseFragment;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.state.EditViewModel;

/**
 * Created by lucas
 * Date: 2020/5/21 20:33
 * 编辑器
 */
public class EditFragment extends BaseFragment {

    private EditViewModel mEditViewModel;

    @Override
    protected void initViewModel() {
        mEditViewModel = getFragmentViewModel(EditViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_edit, mEditViewModel);
    }
}
