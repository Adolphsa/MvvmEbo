package com.lucas.ebo.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.lucas.architecture.utils.StatusBarUtil;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.state.MainViewModel;
import com.lucas.ebo.databinding.ActivityMainBinding;
import com.lucas.ebo.ui.base.BaseActivity;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.page.EditFragment;
import com.lucas.ebo.ui.main.page.ExploreFragment;
import com.lucas.ebo.ui.main.page.MeFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * Created by lucas
 * <p>
 * Date: 2020/4/1 16:50
 * <p>
 * Description:
 */
public class MainActivity extends BaseActivity {

    private MainViewModel mMainViewModel;
    private ActivityMainBinding mMainBinding;

    private List<Fragment> mFragments;

    @Override
    protected void initViewModel() {
        mMainViewModel = getActivityViewModel(MainViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, mMainViewModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = (ActivityMainBinding) getBinding();

        StatusBarUtil.setDarkMode(this);

        initFragment();
        initBottomTab();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new ExploreFragment());
        mFragments.add(new EditFragment());
        mFragments.add(new MeFragment());

        //默认选中第二个
        commitAllowingStateLoss(2);
    }

    private void initBottomTab() {
        NavigationController navigationController = mMainBinding.bottomTab.custom()
                .addItem(newItem(R.drawable.icon_explore, R.drawable.icon_explore_select, ""))
                .addItem(newItem(R.drawable.icon_edit, R.drawable.icon_edit_select, ""))
                .addItem(newItem(R.drawable.icon_my, R.drawable.icon_my_select, ""))
                .build();

        navigationController.setSelect(2);
        //底部点击事件
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                commitAllowingStateLoss(index);
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        return normalItemView;
    }

    private void commitAllowingStateLoss(int position) {
        hideAllFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(position + "");
        if (currentFragment != null) {
            transaction.show(currentFragment);
        } else {
            currentFragment = mFragments.get(position);
            transaction.add(R.id.container, currentFragment, position + "");
        }
        transaction.commitAllowingStateLoss();
    }

    //隐藏所有Fragment
    private void hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(i + "");
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }
}
