package com.sheygam.masa_2018_g1_28_11_18_cw;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputName;
    private Button saveBtn, loadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputName = findViewById(R.id.input_name);
        saveBtn = findViewById(R.id.save_btn);
        loadBtn = findViewById(R.id.load_btn);

        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.save_btn){
            save();
        }else if(v.getId() == R.id.load_btn){
            load();
        }
    }

    private void save() {
        SharedPreferences sp = getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NAME",inputName.getText().toString());
        editor.commit();
    }

    private void load() {
        SharedPreferences sp = getSharedPreferences("DATA",MODE_PRIVATE);
        String name = sp.getString("NAME","Noname");
        inputName.setText(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_item){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.done_item){
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
