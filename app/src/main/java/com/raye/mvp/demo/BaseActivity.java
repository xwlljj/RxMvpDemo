package com.raye.mvp.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by XieWei on 16/8/1.
 */
public class BaseActivity extends AppCompatActivity {
    private static List<Activity> activitys = new LinkedList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitys.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activitys.remove(this);
    }
}
