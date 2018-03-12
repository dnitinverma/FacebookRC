# FBSignIn

FBSignIn is a library which makes signin easier.

<b>Features:</b>
<ul>
  <li>FB SignIn</li>
  <li>Fetch User Details(FbID, Name, Email, UserImage)</li>
 <li>Fetch Friends</li>
</ul>

<b>Getting started:</b><br>

<ul>
  <li>First of all, you need to register you application with facebook developer (https://developers.facebook.com/quickstarts/?platform=android)</li>
  <li>Add fblibrary into your project as module.</li>
<li> Add below line under <b>dependencies{..}</b> tag of app.gradle of your project
<br>
<b>compile project(path: ':fblibrary')</b></li>
<li> Add below code into manifest file (You will get fb id from facebook developer site)
<br>
<b> Under Metadata Tag<br>
            android:name="com.facebook.sdk.ApplicationId"<br>
   android:value="@string/facebook_app_id" /></b></li>
<li> Call initializeFB method from onCreate method
<br>

           private void initializeFB() {
                mFBSignInAI = new FBSignInAI();
                mFBSignInAI.setActivity(MainActivity.this);
                mFBSignInAI.setCallback(this);
            }

after this you can call login or getFriends method using mFBSignInAI.
<li>Implement FBSignCallback in your activity or fragment.</li>
<li> Add below code into onActivityResultMethod
<br>
<b> if(64206 == requestCode)
<br>
      mFBSignInAI.setActivityResult(requestCode, resultCode, data);
</b></li>
</ul>

<b>How to parse result data</b><br>

        String name = jsonObject.getString("name");
        if (jsonObject.has("email")) {
           String email = jsonObject.getString("email");
        }
        String social_id = jsonObject.getString("id");
        if (jsonObject.has("picture")) {
           String userProfilePicUrl = "https://graph.facebook.com/" + jsonObject.getString("id") + "/picture?width=2000";
        }

<b>How to Get HashKey</b><br>

    try {
        PackageInfo info = getPackageManager().getPackageInfo(
        getPackageName(), PackageManager.GET_SIGNATURES);
        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
        }
    } catch (NameNotFoundException e) {
    } catch (NoSuchAlgorithmException e) {
    }
    
    
<b>Releases</b><br>
<ul>
  <li>Version 1.1 - 14 Nov 2017 <br>
  Remove deprecated function FacebookSdk.sdkInitialize(getApplicationContext());</li>

   <li>Version 1.2 - 12 March 2018 <br>
    Resolve context issue</li>
  
</ul>


