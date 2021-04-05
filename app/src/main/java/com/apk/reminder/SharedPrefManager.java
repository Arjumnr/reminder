//package com.apk.reminder;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import com.apk.reminder.Model.User;
//
//public class SharedPrefManager {
//
//    //the constants
//    private static final String SHARED_PREF_NAME = "mUser";
//    private static final String KEY_ID = "keyiduser";
//    private static final String KEY_PROFESI = "keyprofesi";
//    private static final String KEY_NAME = "keyusername";
//    private static final String KEY_EMAIL = "keyemail";
//    private static final String KEY_NOHP= "nohp";
//    private static final String KEY_PASSWORD= "password";
//    private static final String KEY_STATUS= "status";
//
//
//
//    private static SharedPrefManager mInstance;
//    private static Context mCtx;
//
//    private SharedPrefManager(Context context) {
//        mCtx = context;
//    }
//
//    public static synchronized SharedPrefManager getInstance(Context context) {
//        if (mInstance == null) {
//            mInstance = new SharedPrefManager(context);
//        }
//        return mInstance;
//    }
//
//    //method to let the user login
//    //this method will store the user data in shared preferences
//    public void userLogin(User user) {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_ID, user.getId());
//        editor.putString(KEY_PROFESI, user.getProfesi());
//        editor.putString(KEY_NAME, user.getName());
//        editor.putString(KEY_EMAIL, user.getEmail());
//        editor.putString(KEY_NOHP,user.getNo_hp());
//        editor.putString(KEY_PASSWORD,user.getPassword());
//        editor.putString(KEY_STATUS,user.getStatus());
//        editor.apply();
//    }
//
//    public void komentarActive(User user) {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//    }
//    //    public  void setImg(String imgSm, String imgLg){
////        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
////        SharedPreferences.Editor editor = sharedPreferences.edit();
////        editor.putString(KEY_IMAGESM, imgSm);
////        editor.putString(KEY_IMAGESM, imgLg);
////        editor.apply();
////    }
//    //this method will checker whether user is already logged in or not
//    public boolean isLoggedIn() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_ID, null) != null;
//    }
//
//    //this method will give the logged in user
//    public User getUser() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new User(
//                sharedPreferences.getString(KEY_ID, null),
//                sharedPreferences.getString(KEY_PROFESI, null),
//                sharedPreferences.getString(KEY_NAME, null),
//                sharedPreferences.getString(KEY_EMAIL, null),
//                sharedPreferences.getString(KEY_NOHP, null),
//                sharedPreferences.getString(KEY_PASSWORD, null),
//                sharedPreferences.getString(KEY_STATUS, null)
//        );
//    }
//
//    //this method will logout the user
//    public void logout() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
//    }
//}
