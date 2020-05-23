package com.lucas.ebo.ui.main.page;

import androidx.fragment.app.Fragment;

import com.lucas.ebo.R;
import com.lucas.ebo.ui.base.BaseFragment;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.state.PetViewModel;

/**
 * Created by lucas
 * Date: 2020/5/21 20:35
 * 宠物圈
 */
public class PetFragment extends BaseFragment {

    private PetViewModel mPetViewModel;

    @Override
    protected void initViewModel() {
        mPetViewModel = getFragmentViewModel(PetViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_pet, mPetViewModel);
    }
}
