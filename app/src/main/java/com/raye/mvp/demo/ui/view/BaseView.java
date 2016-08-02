package com.raye.mvp.demo.ui.view;

/**
 * Created by XieWei on 16/8/1.
 */
public interface BaseView {

    void onDismissProgress();

    void onShowProgress();

    void onShowErrTips(String msg);
}
