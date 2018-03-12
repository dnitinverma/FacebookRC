package com.dnitinverma.facebooksignin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.dnitinverma.fblibrary.FBSignInAI;
import com.dnitinverma.fblibrary.interfaces.FBSignCallback;
import com.facebook.FacebookException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity implements FBSignCallback {
    private int FB_LOGIN_REQUEST_CODE = 64206;  //Fb Default request code
    private FBSignInAI mFBSignInAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFB();
    }

    /*
    *  Initialize FB instance
    */
    private void initializeFB() {
        mFBSignInAI = new FBSignInAI();
        mFBSignInAI.setActivity(MainActivity.this);
        mFBSignInAI.setCallback(this);
    }

    /*
    *  Sign In Method
    */
    public void doLogin(View view){
        if(mFBSignInAI!=null)
        mFBSignInAI.doSignIn();

    }

    /*
    *  Sign out Method
    */
    public void doLogout(View view){
        if(mFBSignInAI!=null)
        mFBSignInAI.doSignOut();
    }

    /*
   *  Get Friends
   */
    public void getFriends(View view){
        if(mFBSignInAI!=null)
        mFBSignInAI.getFriends();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(FB_LOGIN_REQUEST_CODE == requestCode) {
            mFBSignInAI.setActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void fbSignInSuccessResult(JSONObject jsonObject) {
        String name,email = null,gender = null,social_id,userProfilePicUrl = null,ageRange;
        try {
            name = jsonObject.getString("name");
            if (jsonObject.has("email")) {
                email = jsonObject.getString("email");
            }
            social_id = jsonObject.getString("id");
            if (jsonObject.has("gender")) {
                gender = jsonObject.getString("gender");
            }
            if (jsonObject.has("picture")) {
                userProfilePicUrl = "https://graph.facebook.com/" + jsonObject.getString("id") + "/picture?width=2000";
            }
            ((TextView)findViewById(R.id.tv_name)).setText("Name : "+name);
            ((TextView)findViewById(R.id.tv_id)).setText("Social Id: "+social_id);
            ((TextView)findViewById(R.id.tv_email)).setText("Email: "+ email);
            ((TextView)findViewById(R.id.tv_gender)).setText("Gender: "+ gender);
            ((TextView)findViewById(R.id.tv_picture)).setText("Picture: "+userProfilePicUrl);
            ((TextView)findViewById(R.id.tv_link)).setText("Profile Link: "+"https://www.facebook.com/"+social_id);
        }
        catch (JSONException e)
        {e.printStackTrace();}
    }

    @Override
    public void fbSignOutSuccessResult() {
        Toast.makeText(this,"Sign out successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    public void fbSignInFailure(FacebookException exception) {
        Toast.makeText(MainActivity.this, exception.getMessage().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fbSignInCancel() {
        Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fbFriends(JSONArray data) {
        if(data != null)
         ((TextView)findViewById(R.id.tv_name)).setText("Total Friens : "+ data.length());
        else
            ((TextView)findViewById(R.id.tv_name)).setText("Total Friens : 0" );
        //Parsing of friends data
//        try {
//            for (int i = 0; i < data.length(); i++) {
//                JSONObject dataObject = data.getJSONObject(i);
//                String fbId = dataObject.getString("id");
//                String fbName = dataObject.getString("name");
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }

}
