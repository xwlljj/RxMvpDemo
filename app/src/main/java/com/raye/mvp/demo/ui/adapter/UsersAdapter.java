package com.raye.mvp.demo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raye.mvp.demo.BaseApp;
import com.raye.mvp.demo.R;
import com.raye.mvp.demo.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by XieWei on 16/8/1.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private static final String TAG = "UsersAdapter";
    private ArrayList<User> users;

    public UsersAdapter() {
        users = new ArrayList<>();
    }

    public void addItem(User user) {
        users.add(user);
        notifyItemChanged(users.size() - 1);
    }

    public void addItems(List<User> users) {
        int start = this.users.size() - 1;
        start = start < 0 ? 0 : start;
        this.users.addAll(users);
        notifyItemRangeChanged(start, users.size());
    }

    public User getItem(int position) {
        if (position < 0 || position > users.size() - 1) {
            return null;
        }
        return users.get(position);
    }

    public void destory() {
        users.clear();
        users = null;
    }

    private static int uc = 0;
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder called " + (uc++) + " times");
        return new UserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.setUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvUser = (TextView) itemView.findViewById(R.id.tv_user);
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, getLayoutPosition());
                }
            });
        }

        public void setUser(User user) {
            if (user == null) {
                return;
            }
            tvUser.setText(String.format(Locale.getDefault(),
                    BaseApp.getApp().getString(R.string.main_user),
                    user.getName(), Integer.valueOf(user.getAge()),
                    user.getSex() == 0 ? "男" : "女"));
        }
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
