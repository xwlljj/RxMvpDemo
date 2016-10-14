package com.xwlljj.avlibrary;

import android.util.Log;

import com.tencent.TIMAddFriendRequest;
import com.tencent.TIMFriendResult;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XieWei on 16/9/5.
 */
public class FriendShipManager {
    private static final String TAG = "FriendShipManager";

    public void addFriend(String identifier) {
        ArrayList<TIMAddFriendRequest> reqList = new ArrayList<>();
        TIMAddFriendRequest req = new TIMAddFriendRequest();
        req.setAddrSource("tim demo");
        req.setAddWording("加我");
        req.setIdentifier(identifier);
        req.setRemark("谢");
        reqList.add(req);
        TIMFriendshipManager.getInstance().addFriend(reqList, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "add friend fail ! error code is " + i + ", des is " + s);
            }

            @Override
            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                Log.i(TAG, "add friend success ! " + timFriendResults);
            }
        });
    }

    public void deleteFriend(String identifier) {
//        TIMFriendshipManager.getInstance().delFriend();
        
    }

    public void applyAdded() {

    }

    public void refuseAdded() {

    }


}
