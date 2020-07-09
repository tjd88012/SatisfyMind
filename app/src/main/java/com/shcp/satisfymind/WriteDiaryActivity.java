package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WriteDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);
    }

    public void clickSaveDiary(View view) {
    }

    public void clickCancelDiary(View view) {
        Intent intent=new Intent(this,MyDiaryActivity.class);
        startActivity(intent);
        finish();
    }
}
