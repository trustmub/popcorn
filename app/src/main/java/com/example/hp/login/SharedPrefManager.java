package com.example.hp.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.hp.login.model.User;
import com.example.hp.login.ui.LoginActivity;

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_FIRSTNAME = "keyfirstname";
    private static final String KEY_SIRNAME = "keysirname";
    private static final String KEY_EMAILADDRESS = "keyemail_address";
    private static final String KEY_PHONENUMBER = "keyphone_number";
    private static final String KEY_BIRTHDATE = "keybirth_date";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_FIRSTNAME, user.getFirst_name());
        editor.putString(KEY_SIRNAME, user.getSir_name());
        editor.putString(KEY_EMAILADDRESS, user.getEmail_address());
        editor.putString(KEY_PHONENUMBER, user.getPhone_number());
        editor.putString(KEY_BIRTHDATE, user.getBirth_date());
        editor.putString(KEY_GENDER, user.getGender());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_FIRSTNAME, null),
                sharedPreferences.getString(KEY_SIRNAME, null),
                sharedPreferences.getString(KEY_PHONENUMBER, null),
                sharedPreferences.getString(KEY_BIRTHDATE, null),
                sharedPreferences.getString(KEY_EMAILADDRESS, null),
                sharedPreferences.getString(KEY_GENDER, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}