package com.raye.mvp.demo.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raye.mvp.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XieWei on 16/8/23.
 * 实现 ViewPager 的无限循环,核心在创建一个 ViewPool
 */
public class WeeksAdapter extends PagerAdapter {
    private Context context;
    private List<View> viewPool;
    private List<Object> data;

    public WeeksAdapter(Context context, List<Object> data) {
        this.context = context;
        this.data = data;
        viewPool = new ArrayList<>(6);
        initViewPool();
    }

    private void initViewPool() {
        LayoutInflater li = LayoutInflater.from(context);
        for (int i = 0; i < 6; i++) {
            View v = li.inflate(R.layout.main_user_item, null);
            viewPool.add(v);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object item = data.get(position);
        View view = viewPool.get(position % viewPool.size());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewPool.get(position % viewPool.size()));
    }

}
