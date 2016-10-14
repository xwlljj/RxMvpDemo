package com.xwlljj.avlibrary;

import android.util.Log;

import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessageListener;

import java.util.List;
import java.util.Observable;

/**
 * Created by XieWei on 16/9/7.
 * 接收消息的入口
 */
public class MessageEvent extends Observable implements TIMMessageListener {
    private static final String TAG = "MessageEvent";
    private static volatile MessageEvent instance;

    private MessageEvent() {
        TIMManager.getInstance().addMessageListener(this);
    }

    public static MessageEvent getInstance() {
        if (instance == null) {
            synchronized (MessageEvent.class) {
                if (instance == null) {
                    instance = new MessageEvent();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        Log.i(TAG, "onNewMessages(" + list + ")");
        for (TIMMessage msg : list) {
            setChanged();
            notifyObservers(msg);
        }
        return true;
    }

    public void onNewMessage(TIMMessage msg) {
        setChanged();
        notifyObservers(msg);
    }

    public void clear() {
        instance = null;
    }

}
