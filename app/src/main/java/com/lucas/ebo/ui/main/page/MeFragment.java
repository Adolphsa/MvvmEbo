package com.lucas.ebo.ui.main.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.coder.zzq.smartshow.dialog.DialogBtnClickListener;
import com.coder.zzq.smartshow.dialog.EnsureDialog;
import com.coder.zzq.smartshow.dialog.SmartDialog;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.architecture.utils.ThreadUtils;
import com.lucas.ebo.BR;
import com.lucas.ebo.R;
import com.lucas.ebo.bridge.request.AccountRequestViewModel;
import com.lucas.ebo.data.bean.response.DeviceInfoResultBean;
import com.lucas.ebo.data.repository.DataRepository;
import com.lucas.ebo.data.repository.database.EboRoomDatabase;
import com.lucas.ebo.data.repository.database.UserInfo;
import com.lucas.ebo.ui.activity.add.AddDeviceTipActivity;
import com.lucas.ebo.ui.base.BaseFragment;
import com.lucas.ebo.ui.base.DataBindingConfig;
import com.lucas.ebo.ui.main.adapter.DeviceListAdapter;
import com.lucas.ebo.ui.main.state.MeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas
 * Date: 2020/5/21 20:31
 * 我的
 */
public class MeFragment extends BaseFragment {

    private static final String TAG = "MeFragment";

    private MeViewModel mMeViewModel;
    private AccountRequestViewModel mAccountRequestViewModel;
    private List<DeviceInfoResultBean> mDeviceInfoList;

    private EnsureDialog mEnsureDialog;

    @Override
    protected void initViewModel() {
        mMeViewModel = getFragmentViewModel(MeViewModel.class);
        mAccountRequestViewModel = getFragmentViewModel(AccountRequestViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_me, mMeViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.adapter, new DeviceListAdapter(getContext()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDeviceInfoList = new ArrayList<>();
        DeviceInfoResultBean d1 = new DeviceInfoResultBean();
        d1.setRobot_name("Robot1");
        mDeviceInfoList.add(d1);
        DeviceInfoResultBean d2 = new DeviceInfoResultBean();
        d2.setRobot_name("Robot2");
        mDeviceInfoList.add(d2);
        mMeViewModel.list.setValue(mDeviceInfoList);

        mAccountRequestViewModel.emailIsActive.observe(getViewLifecycleOwner(), emailIsActive->{
            LogUtils.d(TAG, "邮件激活状态 = " + emailIsActive);
            if (emailIsActive)
            {
                //账号已激活
                if (mEnsureDialog != null)
                {
                    mEnsureDialog.dismiss();
                }
                updateUserInfo();

            }else {
                //继续显示提示框
                onShowEnsureDialog();
            }
        });

        checkEmailActive();
    }

    /**
     * 更新userInfo数据
     */
    private void updateUserInfo()
    {
        int userId = DataRepository.getInstance().getUserId();
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Object>() {
            @Override
            public Object doInBackground() throws Throwable {
                UserInfo userInfo = EboRoomDatabase.getInstance().userInfoDao().getUserInfoById(userId);
                if (userInfo != null)
                {
                    userInfo.setValid(true);
                    EboRoomDatabase.getInstance().userInfoDao().updateUserInfo(userInfo);
                }

                return null;
            }

            @Override
            public void onSuccess(Object result) {

            }
        });
    }


    /**
     * 检查Email是否激活
     */
    private void checkEmailActive() {
        //查询账号是否激活
        int userId = DataRepository.getInstance().getUserId();
        LogUtils.d(TAG, "mvvm userId = " + userId);
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<UserInfo>() {
            @Override
            public UserInfo doInBackground() throws Throwable {
                return EboRoomDatabase.getInstance().userInfoDao().getUserInfoById(userId);
            }

            @Override
            public void onSuccess(UserInfo userInfo) {
                if (userInfo != null) {
                    boolean emailIsValid = userInfo.isValid();
                    if (emailIsValid) {
                        //账号已经激活 获取设备列表

                    } else {
                        //账号未激活 弹出激活对话框
                        onShowEnsureDialog();
                    }
                }
            }
        });
    }

    private void onShowEnsureDialog() {
        if (mEnsureDialog == null) {
            mEnsureDialog = new EnsureDialog()
                    .title("激活邮箱")
                    .message("我们已经发送电子邮件至您填写的邮箱。请检查您的邮箱并点击邮件中的激活链接完成验证")
                    .confirmBtn("确定", new DialogBtnClickListener() {
                        @Override
                        public void onBtnClick(SmartDialog dialog, int which, Object data) {
                            dialog.dismiss();
                            mAccountRequestViewModel.checkEmailActiveStatus("1");
                        }
                    })
                    .cancelBtn("重新发件", new DialogBtnClickListener() {
                        @Override
                        public void onBtnClick(SmartDialog smartDialog, int i, Object o) {
                            //重新发送邮件验证码
                            mAccountRequestViewModel.checkEmailActiveStatus("");
                        }
                    });
        }
        mEnsureDialog.showInFragment(this);
    }

    public class ClickProxy {

        public void add() {
            Intent intent = new Intent(getActivity(), AddDeviceTipActivity.class);
            startActivity(intent);
        }

    }
}
