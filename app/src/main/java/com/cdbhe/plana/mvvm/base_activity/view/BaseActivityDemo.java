package com.cdbhe.plana.mvvm.base_activity.view;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ListView;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.base_activity.adapter.BaseActivityButtonAdapter;
import com.cdbhe.plana.mvvm.base_activity.listener.OnBaseActivityButtonClickListener;
import com.cdbhe.plana.mvvm.base_activity.model.ButtonModel;
import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.http.common.RequestParams;
import com.cdbhe.plib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivityDemo extends MyBaseActivity implements OnBaseActivityButtonClickListener,BaseActivity.OnTitleBarListener {

    @BindView(R.id.listView)
    ListView listView;
    private List<ButtonModel> buttonModelList;
    private BaseActivityButtonAdapter adapter;
    private boolean isShowStatusBar = false;//是否显示状态栏
    private final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_base_demo;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("BaseActivity");//设置标题
        initListView();//初始化列表
    }

    /**
     * 初始化按钮列表
     */
    private void initListView() {
        buttonModelList = new ArrayList<>();
        buttonModelList.add(new ButtonModel("6.0+权限动态请求"));
        buttonModelList.add(new ButtonModel("是否显示状态栏"));
        buttonModelList.add(new ButtonModel("设置状态栏颜色（沉浸式）"));
        buttonModelList.add(new ButtonModel("开启菊花加载进度窗"));
        buttonModelList.add(new ButtonModel("隐藏标题栏"));
        buttonModelList.add(new ButtonModel("显示标题栏"));
        buttonModelList.add(new ButtonModel("隐藏返回icon"));
        buttonModelList.add(new ButtonModel("显示返回icon"));
        buttonModelList.add(new ButtonModel("设置标题"));
        buttonModelList.add(new ButtonModel("标题栏显示更多icon"));
        buttonModelList.add(new ButtonModel("替换标题栏更多icon"));
        buttonModelList.add(new ButtonModel("设置标题栏背景颜色"));
        buttonModelList.add(new ButtonModel("设置返回icon"));
        buttonModelList.add(new ButtonModel("设置标题颜色"));
        buttonModelList.add(new ButtonModel("设置标题字体大小"));

        adapter = new BaseActivityButtonAdapter(this, buttonModelList, R.layout.adapter_base_activity_button_item, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onButtonClick(View view, ButtonModel buttonModel, int position) {
        switch (position) {
            case 0://6.0+权限动态请求
                requestPermission(new String[]{Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);
                break;
            case 1://是否显示状态栏
                setIsShowStatusBar(isShowStatusBar = !isShowStatusBar);
                break;
            case 2://设置状态栏颜色（沉浸式）
                setStatusBarColor(Color.parseColor("#445b53"));
                break;
            case 3://点击 开启菊花加载弹窗 showDialog()
                showDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDialog();
                        ToastUtils.showShort(BaseActivityDemo.this,"调用closeDialog()关闭");
                    }
                },1500);
                break;
            case 4://隐藏标题栏
                hideTitleBar();
                break;
            case 5://显示标题栏
                showTitleBar();
                break;
            case 6://隐藏返回icon
                hideEsc();
                break;
            case 7://显示返回icon
                showEsc();
                break;
            case 8://设置标题
                setTitle("新标题");
                break;
            case 9://标题栏显示更多icon
                showMore(this);
                break;
            case 10://替换标题栏更多icon
                replaceMoreIcon(R.mipmap.ic_more);
                break;
            case 11://设置标题栏背景颜色
                setTitleBarBg(Color.RED);
                break;
            case 12://设置返回icon
                setEscIcon(R.mipmap.ic_esc);
                break;
            case 13://设置标题颜色
                setTitleTextColor(Color.BLACK);
                break;
            case 14://设置标题字体大小
                setTitleTextSize(18);
                break;
            default:
                break;
        }
    }

    @Override
    public void clickMore(View view) {
        ToastUtils.showShort(this,"点击了更多icon");
    }

    @Override
    public void permissionSucceed(int code) {
        super.permissionSucceed(code);
        ToastUtils.showShort(this,"权限申请成功");
    }

    @Override
    public void permissionFailing(int code) {
        super.permissionFailing(code);
        ToastUtils.showShort(this,"权限申请失败");
    }
}
