package com.raye.mvp.demo.presenter.impl;

import android.util.Log;

import com.raye.mvp.demo.model.biz.MainModel;
import com.raye.mvp.demo.model.biz.impl.MainModelimpl;
import com.raye.mvp.demo.model.entity.User;
import com.raye.mvp.demo.presenter.MainPresenter;
import com.raye.mvp.demo.ui.view.MainView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XieWei on 16/8/1.
 */
public class MainPresenterImpl implements MainPresenter {
    private static final String TAG = "MainPresenterImpl";
    private MainModel mainModel;
    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelimpl();
    }

    @Override
    public void onDestory() {
        mainModel = null;
        mainView = null;
    }

    @Override
    public void loadData() {
        Log.d(TAG, "loadData()");
        mainModel.loadData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCommpleted");
                        mainView.onDismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError()");
                        mainView.onDismissProgress();
                        mainView.onShowErrTips(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext(" + user + ")");
                        mainView.onShowUser(user);
                    }
                });
    }

    @Override
    public void loadDatas() {
        long tmp = System.currentTimeMillis();
        Log.d(TAG, "loadDatas()--->" + tmp);
        mainModel.loadDatas().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                        mainView.onDismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.onDismissProgress();
                        mainView.onShowErrTips(e.getMessage());
                    }

                    @Override
                    public void onNext(List<User> users) {
                        Log.d(TAG, Thread.currentThread().getName());
                        mainView.onShowUsers(users);
                    }
                });
        Log.d(TAG, "loadDatas()--->" + (System.currentTimeMillis() - tmp));
    }
}
