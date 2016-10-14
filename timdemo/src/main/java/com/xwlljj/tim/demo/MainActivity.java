package com.xwlljj.tim.demo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xwlljj.tim.demo.ui.ContactFragment;
import com.xwlljj.tim.demo.ui.MeFragment;
import com.xwlljj.tim.demo.ui.MsgFragment;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rgTabs;
    private RadioButton[] rbTabItems;
    private Fragment[] pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgTabs = (RadioGroup) findViewById(R.id.rg_tabs);
        rbTabItems = new RadioButton[rgTabs.getChildCount()];
        rbTabItems[0] = (RadioButton) findViewById(R.id.rb_msgs);
        rbTabItems[1] = (RadioButton) findViewById(R.id.rb_contact);
        rbTabItems[2] = (RadioButton) findViewById(R.id.rb_profiles);
        pages = new Fragment[3];
        pages[0] = new MsgFragment();
        pages[1] = new ContactFragment();
        pages[2] = new MeFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fl_container, pages[0], "page0");
        ft.add(R.id.fl_container, pages[1], "page1");
        ft.add(R.id.fl_container, pages[2], "page2");
        ft.show(pages[0]);
        ft.hide(pages[1]);
        ft.hide(pages[2]);
        ft.commit();

        rgTabs.setOnCheckedChangeListener(this);
        rgTabs.check(R.id.rb_msgs);
//        AVManager avManager = new AVManager();
//        avManager.prepareAVContext(BaseApplication.getInstance());
//        avManager.login(BaseApplication.getInstance());
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.rb_msgs:
                ft.show(pages[0]);
                ft.hide(pages[1]);
                ft.hide(pages[2]);
                break;
            case R.id.rb_contact:
                ft.show(pages[1]);
                ft.hide(pages[0]);
                ft.hide(pages[2]);
                break;
            case R.id.rb_profiles:
                ft.show(pages[2]);
                ft.hide(pages[0]);
                ft.hide(pages[1]);
                break;
        }
        ft.commit();
    }
}
