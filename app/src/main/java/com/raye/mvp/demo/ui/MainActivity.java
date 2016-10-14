package com.raye.mvp.demo.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.raye.mvp.demo.BaseActivity;
import com.raye.mvp.demo.R;
import com.raye.mvp.demo.model.entity.User;
import com.raye.mvp.demo.presenter.MainPresenter;
import com.raye.mvp.demo.presenter.impl.MainPresenterImpl;
import com.raye.mvp.demo.ui.adapter.UsersAdapter;
import com.raye.mvp.demo.ui.view.MainView;
import com.raye.mvp.demo.widget.RecyclerViewDivider;
import com.xwlljj.uilibrary.QuickIndexBar;

import java.util.List;

/**
 * Created by XieWei on 16/8/1.
 */
public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = "MainActivity";
    private RecyclerView rvUsers;
    private MainPresenter mainPresenter;
    private UsersAdapter usersAdapter;
    private QuickIndexBar qibIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        qibIndex = (QuickIndexBar) findViewById(R.id.qib_index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvUsers = (RecyclerView) findViewById(R.id.rv_users);
        rvUsers.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        rvUsers.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
        usersAdapter = new UsersAdapter();
        rvUsers.setAdapter(usersAdapter);

        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.loadData();
//        mainPresenter.loadDatas();

        usersAdapter.setOnItemClickListener((view, position) ->
                Toast.makeText(this, usersAdapter.getItem(position).toString(),
                        Toast.LENGTH_LONG).show());

        qibIndex.setIndexTextListener((txt) -> {
            Log.d(TAG, txt);
        });
    }

    @Override
    public void onShowUser(User user) {
        if (user == null) {
            return;
        }
        Log.d(TAG, user.toString());
        usersAdapter.addItem(user);
    }

    @Override
    public void onShowUsers(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        Log.d(TAG, "onShowUsers(users size is " + users.size() + ")");
        usersAdapter.addItems(users);
    }

    @Override
    public void onDismissProgress() {
        Log.d(TAG, "onDismissProgress()");
        rvUsers.smoothScrollToPosition(usersAdapter.getItemCount() - 1);
    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onShowErrTips(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestory();
        rvUsers.setAdapter(null);
        usersAdapter.destory();
        usersAdapter = null;
    }
}
