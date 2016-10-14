package com.xwlljj.tim.demo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwlljj.tim.demo.R;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by XieWei on 16/9/6.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private LinkedList<Object> data;

    public ChatAdapter() {
        data = new LinkedList<>();
    }

    public void addItems(Collection<Object> items) {
        if (items == null) {
            return;
        }
        data.addAll(items);
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_msg_item, null);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Object item = data.get(position);
        //TODO 绑定数据
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        public ChatViewHolder(View itemView) {
            super(itemView);
        }
    }
}
