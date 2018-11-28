package com.sheygam.masa_2018_g1_28_11_18_cw;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String SP_AUTH = "AUTH";
    public static final String SP_KEY = "CURRENT";
    private static final int PROFILE_ACT = 0x01;

    private EditText inputEmail, inputPassword;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isLoggined()){
            startActivityForResult(new Intent(this,ProfileActivity.class),PROFILE_ACT);
        }
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_btn){
            login();
        }
    }


    private boolean isLoggined(){
        SharedPreferences sp = getSharedPreferences(SP_AUTH,MODE_PRIVATE);
        String curr = sp.getString(SP_KEY,null);
        if(curr != null){
            return true;
        }
        return false;
//        return getSharedPreferences(SP_AUTH,MODE_PRIVATE)
//                .getString(SP_KEY,null) != null;
    }


    public void login(){
       getSharedPreferences(SP_AUTH,MODE_PRIVATE)
                .edit()
                .putString(SP_KEY,inputEmail.getText() + "&" + inputPassword.getText())
                .apply();
       startActivityForResult(new Intent(this,ProfileActivity.class),PROFILE_ACT);
    }


    public void logout(){
        getSharedPreferences(SP_AUTH,MODE_PRIVATE)
                .edit()
                .remove(SP_KEY)
                .apply();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == PROFILE_ACT){
            logout();
        }else{
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
