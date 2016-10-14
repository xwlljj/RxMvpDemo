package com.xwlljj.tim.demo.presenter;

import com.tencent.TIMMessage;
import com.xwlljj.avlibrary.MessageEvent;
import com.xwlljj.tim.demo.ui.view.ChatView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by XieWei on 16/9/7.
 */
public class ChatPresenter implements Observer, IPresenter {
    private ChatView chatView;

    public ChatPresenter(ChatView chatView) {
        this.chatView = chatView;
        MessageEvent.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof MessageEvent) {
            TIMMessage msg = (TIMMessage) o;

        }
    }

    @Override
    public void destory() {
        MessageEvent.getInstance().deleteObserver(this);
        chatView = null;
    }
}
