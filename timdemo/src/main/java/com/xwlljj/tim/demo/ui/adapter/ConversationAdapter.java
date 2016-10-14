package com.xwlljj.tim.demo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwlljj.tim.demo.R;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by XieWei on 16/9/6.
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {
    private LinkedHashMap<Long, Object> data;
    private List<Long> keys;

    public ConversationAdapter() {
        data = new LinkedHashMap<>();
        keys = new LinkedList<>();
        keys.clear();
        keys.addAll(data.keySet());
    }

    public void putItems(Map<Long, Object> items) {
        if (items == null) {
            return;
        }
        data.putAll(items);
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_item, null);
        ConversationViewHolder holder = new ConversationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ConversationViewHolder extends RecyclerView.ViewHolder {

        public ConversationViewHolder(View itemView) {
            super(itemView);
        }
    }
}
