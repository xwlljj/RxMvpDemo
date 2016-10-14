package com.xwlljj.tim.demo.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.xwlljj.tim.demo.R;

/**
 * Created by XieWei on 16/9/6.
 */
public class ChatActivity extends Activity {
    private FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fl_container, new ChatFragment(), "chat");
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
