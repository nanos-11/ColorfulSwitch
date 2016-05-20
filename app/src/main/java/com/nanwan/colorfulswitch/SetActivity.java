package com.nanwan.colorfulswitch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import colorful.Colorful;

public class SetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mSetChange;
    private Colorful mColorful;
    private ThemeUtil mThemeUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mSetChange = (Button) findViewById(R.id.set_change);
    }

    private void initListener() {
        mSetChange.setOnClickListener(this);
    }

    private void initData() {
        setupColorful();
    }

    /**
     * 设置各个视图与颜色属性的关联
     */
    private void setupColorful() {
        mColorful = new Colorful.Builder(this)
                .backgroundDrawable(R.id.rl_set, R.attr.root_view_bg)
                .backgroundColor(R.id.set_change, R.attr.btn_bg) // 设置按钮的背景色
                .textColor(R.id.set_change, R.attr.text_color) // 设置文本颜色
                .create(); // 设置文本颜色
        mThemeUtil = new ThemeUtil(getApplicationContext(), mColorful);
    }

    @Override
    public void onClick(View v) {
        changeThemeWithColorful();
    }

    private void changeThemeWithColorful() {
        mThemeUtil.setTheme();
    }

    private void themeWithColorful() {
        mThemeUtil.setThemeOnCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        themeWithColorful();
    }
}
