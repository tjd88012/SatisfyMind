package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WriteWorryActivity extends AppCompatActivity {

    EditText title, text;
    TextView nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_worry);

        title=findViewById(R.id.write_worry_title_et);
        text=findViewById(R.id.write_worry_text_et);
        nickName=findViewById(R.id.write_worry_nickname);

        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        nickName.setText(preferences.getString("Nickname",""));

    }

    public void clickCancelWorry(View view) {
        finish();
    }

    public void clickSaveWorry(View view) {
    }
}
