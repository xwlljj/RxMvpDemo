package com.xwlljj.avlibrary;

import android.util.Log;

import com.tencent.TIMCallBack;
import com.tencent.TIMFriendAllowType;
import com.tencent.TIMFriendshipManager;

/**
 * Created by XieWei on 16/9/6.
 */
public class ProfileManager {
    private static final String TAG = "ProfileManager";

    /**
     * 设置验证类型
     *
     * @param type
     */
    public void setAllowType(TIMFriendAllowType type) {
        TIMFriendshipManager.getInstance().setAllowType(type, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess() {

            }
        });
    }

    /**
     * 设置昵称
     */
    public void setNick(String nick) {
        TIMFriendshipManager.getInstance().setNickName(nick, new TIMCallBack() {
            @Override
            public void onError(int errorCode, String des) {
                Log.e(TAG, "set nick fail ! error code is " + errorCode + ", des is " + des);
            }

            @Override
            public void onSuccess() {
                Log.i(TAG, "set nick success");
            }
        });
    }

    public void setSelfSignature(String signature) {
        TIMFriendshipManager.getInstance().setSelfSignature(signature, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess() {

            }
        });
    }

    /**
     * 备注好友
     *
     * @param remark
     */
    public void setRemark(final String identifier, String remark) {
        TIMFriendshipManager.getInstance().setFriendRemark(identifier, remark, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess() {

            }
        });
    }
}
