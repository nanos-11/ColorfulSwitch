package com.nanwan.colorfulswitch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import colorful.Colorful;
import colorful.setter.ViewGroupSetter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private Button mChange_btn;
    private Button mSecond_btn;
    private Colorful mColorful;
    private List<String> mNewsList;
    private ThemeUtil mThemeUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mChange_btn = (Button) findViewById(R.id.change_btn);
        mSecond_btn = (Button) findViewById(R.id.second_btn);
    }

    private void initListener() {
        mChange_btn.setOnClickListener(this);
        mSecond_btn.setOnClickListener(this);
    }

    private void initData() {
        // 模拟数据
        mockNews();
        mListView.setAdapter(new NewsAdapter());
        // 为ListView设置要修改的属性,在这里没有对ListView本身的属性做修改
        ViewGroupSetter listViewSetter = new ViewGroupSetter(mListView, 0);
        // 绑定ListView的Item View中的news_title视图，在换肤时修改它的text_color属性
        listViewSetter.childViewTextColor(R.id.news_title, R.attr.text_color);
        // 构建Colorful对象
        mColorful = new Colorful.Builder(this)
                .backgroundDrawable(R.id.root_view, R.attr.root_view_bg) // 设置view的背景图片
                .backgroundColor(R.id.change_btn, R.attr.btn_bg) // 设置按钮的背景色
                .textColor(R.id.change_btn, R.attr.text_color) // 设置文本颜色
                .setter(listViewSetter)           // 手动设置setter
                .create();
        mThemeUtil = new ThemeUtil(getApplicationContext(), mColorful);
    }


    // 切换主题
    private void changeThemeWithColorful() {
        mThemeUtil.setTheme();
    }

    private void themeWithColorful() {
        mThemeUtil.setThemeOnCreate();
    }

    private void mockNews() {
        mNewsList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mNewsList.add("News Title - " + i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_btn:
                changeThemeWithColorful();
                break;
            case R.id.second_btn:
                startActivity(new Intent(MainActivity.this, SetActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * @author mrsimple
     */
    class NewsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mNewsList.size();
        }

        @Override
        public String getItem(int position) {
            return mNewsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NewsViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.news_lv_item, parent, false);
                viewHolder = new NewsViewHolder();
                viewHolder.newsTitleView = (TextView) convertView
                        .findViewById(R.id.news_title);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (NewsViewHolder) convertView.getTag();
            }

            viewHolder.newsTitleView.setText(getItem(position));
            return convertView;
        }

    }

    public static class NewsViewHolder {
        public TextView newsTitleView;
    }

    @Override
    protected void onResume() {
        super.onResume();
        themeWithColorful();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
