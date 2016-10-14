package com.xwlljj.tim.demo.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwlljj.tim.demo.R;

/**
 * Created by XieWei on 16/9/6.
 */
public class MsgFragment extends Fragment {
    private static final String TAG = "MsgFragment";
    private RecyclerView rvConversations;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg, container, false);
        rvConversations = (RecyclerView) view.findViewById(R.id.rv_conversations);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
