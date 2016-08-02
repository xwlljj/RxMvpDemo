package com.raye.mvp.demo.model.biz.impl;

import android.util.Log;

import com.raye.mvp.demo.model.biz.MainModel;
import com.raye.mvp.demo.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by XieWei on 16/8/1.
 */
public class MainModelimpl implements MainModel {
    private static final String TAG = "MainModelimpl";

    private List<User> getUsers() {
        long tmp = System.currentTimeMillis();
        Log.d(TAG, "getUsers()--->" + tmp);
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 600; i++) {
            User u = new User();
            u.setName("user-100" + i);
            u.setAge(i + 10);
            if (i % 2 == 0) {
                u.setSex(1);
            } else {
                u.setSex(0);
            }
            users.add(u);
        }
        Log.d(TAG, "getUsers()--->" + (System.currentTimeMillis() - tmp));
        return users;
    }

    @Override
    public Observable<User> loadData() {
//        return Observable.from(getUsers());
        return Observable.defer(() -> Observable.from(getUsers()));
    }

    @Override
    public Observable<List<User>> loadDatas() {
        return Observable.create(subscriber -> {
            Log.d(TAG, Thread.currentThread().getName());
            List<User> tmp = getUsers();
            for (int i = 0; i < 6; i++) {
                subscriber.onNext(tmp.subList(i * 100, (i * 100) + 100));
                try {
                    Thread.sleep(1000);//模拟耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            subscriber.onCompleted();
//            subscriber.onNext(getUsers());
        });
    }
}
