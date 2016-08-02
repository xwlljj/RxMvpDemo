package com.raye.mvp.demo.ui.view;

import com.raye.mvp.demo.model.entity.User;

import java.util.List;

/**
 * Created by XieWei on 16/8/1.
 */
public interface MainView extends BaseView {

    void onShowUser(User user);

    void onShowUsers(List<User> users);
}
