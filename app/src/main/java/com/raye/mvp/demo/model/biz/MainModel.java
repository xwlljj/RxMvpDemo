package com.raye.mvp.demo.model.biz;

import com.raye.mvp.demo.model.BaseModel;
import com.raye.mvp.demo.model.entity.User;

import java.util.List;

import rx.Observable;

/**
 * Created by XieWei on 16/8/1.
 */
public interface MainModel extends BaseModel {

    Observable<User> loadData();

    Observable<List<User>> loadDatas();
}
