package com.sheygam.masa_2018_g1_28_11_18_cw;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String SP_PROFILE = "PROFILE";
    private TextView nameTxt, phoneTxt, addressTxt, ageTxt;
    private EditText inputName, inputPhone, inputAddress, inputAge;
    private Button logoutBtn, editBtn, saveBtn;
    private LinearLayout textWrapper, inputWrapper;
    private Profile curr;
    private String currKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load();
        setContentView(R.layout.activity_profile);
        nameTxt = findViewById(R.id.name_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        addressTxt = findViewById(R.id.address_txt);
        ageTxt = findViewById(R.id.age_txt);

        inputName = findViewById(R.id.input_name);
        inputPhone = findViewById(R.id.input_phone);
        inputAddress = findViewById(R.id.input_address);
        inputAge = findViewById(R.id.input_age);

        logoutBtn = findViewById(R.id.logout_btn);
        editBtn = findViewById(R.id.edit_btn);
        saveBtn = findViewById(R.id.save_btn);

        inputWrapper = findViewById(R.id.edit_wrapper);
        textWrapper = findViewById(R.id.text_wrapper);

        logoutBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);

        viewMode();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout_btn:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.edit_btn:
                editMode();
                break;
            case R.id.save_btn:
                curr.setName(inputName.getText().toString());
                curr.setPhone(inputPhone.getText().toString());
                curr.setAddress(inputAddress.getText().toString());
                curr.setAge(inputAge.getText().toString());
                save();
                viewMode();
                break;
        }
    }

    private void load(){
        SharedPreferences sp = getSharedPreferences(LoginActivity.SP_AUTH,MODE_PRIVATE);
        currKey = sp.getString(LoginActivity.SP_KEY,null);
        if(currKey == null){
            setResult(RESULT_OK);
            finish();
        }else{
            sp = getSharedPreferences(SP_PROFILE,MODE_PRIVATE);
            String data = sp.getString(currKey,null);
            if(data!=null){
                curr = Profile.newInstance(data);
            }else{
                curr = new Profile("","","","");
            }
        }
    }

    private void save(){
        SharedPreferences sp = getSharedPreferences(SP_PROFILE,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(currKey,curr.toString());
        editor.apply();
    }

    private void viewMode(){
        nameTxt.setText(curr.getName());
        phoneTxt.setText(curr.getPhone());
        addressTxt.setText(curr.getAddress());
        ageTxt.setText(curr.getAge());
        textWrapper.setVisibility(View.VISIBLE);
        inputWrapper.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
        saveBtn.setVisibility(View.GONE);

    }

    private void editMode(){
        inputName.setText(curr.getName());
        inputPhone.setText(curr.getPhone());
        inputAddress.setText(curr.getAddress());
        inputAge.setText(curr.getAge());
        inputWrapper.setVisibility(View.VISIBLE);
        textWrapper.setVisibility(View.GONE);
        editBtn.setVisibility(View.GONE);
        saveBtn.setVisibility(View.VISIBLE);
    }
}
