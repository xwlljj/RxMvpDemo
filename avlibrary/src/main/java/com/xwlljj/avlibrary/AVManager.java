package com.xwlljj.avlibrary;

import android.content.Context;
import android.util.Log;

import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import com.tencent.av.sdk.AVContext;

/**
 * Created by XieWei on 16/8/24.
 */
public class AVManager {
    private static final String TAG = "AVManager";
    private AVContext.Config config;
    private static AVContext avContext;

    public static String getVersion() {
        return AVContext.getVersion();
    }

    public void prepareAVContext(Context context) {
        makeConfig();
        avContext = AVContext.createInstance(context, config);
    }

    public static AVContext getAvContext() {
        return avContext;
    }

    private void makeConfig() {
        if (config == null) {
            config = new AVContext.Config();
            config.accountType = "7083";
            config.identifier = "cisalee";
            config.sdkAppId = 1400013876;
            config.appIdAt3rd = Integer.toString(config.sdkAppId);
        }
    }

    public void login(Context context) {
        TIMManager.getInstance().init(context);

        TIMUser userId = new TIMUser();
        userId.setAccountType(config.accountType);
        userId.setAppIdAt3rd(config.appIdAt3rd);
        userId.setIdentifier(config.identifier);

        TIMManager.getInstance().login(config.sdkAppId, userId, TencentAccountConst.CISA_LEE_SIG,
                new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        Log.d(TAG, "login error : error code is " + i + ", des is " + s);
                    }

                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "login success !");
                        Log.i(TAG, "login user is " + TIMManager.getInstance().getLoginUser());
                        new FriendShipManager().addFriend("xwlljj");
                        new ProfileManager().setNick("xiaoxianrou");

                    }
                });
    }

    public void onDestory() {
        if (avContext != null) {
            avContext.destroy();
            avContext = null;
        }
    }
}
