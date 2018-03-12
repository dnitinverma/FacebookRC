package com.dnitinverma.fblibrary.interfaces;


import com.facebook.FacebookException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Rajat on 21-02-2017.
 */

public interface FBSignCallback {
    void fbSignInSuccessResult(JSONObject jsonObject);
    void fbSignOutSuccessResult();
    void fbSignInFailure(FacebookException exception);
    void fbSignInCancel();
    void fbFriends(JSONArray data);
}
