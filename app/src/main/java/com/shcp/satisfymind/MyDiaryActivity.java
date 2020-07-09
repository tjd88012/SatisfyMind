package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);
    }

    public void clickDiaryWrite(View view) {

        Intent intent = new Intent(this,WriteDiaryActivity.class);
        //받아올 결과를 받기 위해 startActivityForResult를 써야하지만 임시로 가기만 함
        startActivity(intent);
        finish();

    }
}
