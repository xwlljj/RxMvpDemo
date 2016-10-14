package com.xwlljj.tim.demo.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwlljj.tim.demo.R;
import com.xwlljj.tim.demo.presenter.ChatPresenter;
import com.xwlljj.tim.demo.ui.view.ChatView;

/**
 * Created by XieWei on 16/9/6.
 */
public class ChatFragment extends Fragment implements ChatView {
    private static final String TAG = "ChatFragment";
    private ChatPresenter chatPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chatPresenter = new ChatPresenter(this);
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
