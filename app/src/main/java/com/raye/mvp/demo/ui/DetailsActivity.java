package com.raye.mvp.demo.ui;

import android.os.Bundle;

import com.raye.mvp.demo.BaseActivity;
import com.raye.mvp.demo.R;
import com.raye.mvp.demo.ui.view.UserDetailsView;

/**
 * Created by XieWei on 16/8/2.
 */
public class DetailsActivity extends BaseActivity implements UserDetailsView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
    }


    @Override
    public void onDismissProgress() {

    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onShowErrTips(String msg) {

    }
}
