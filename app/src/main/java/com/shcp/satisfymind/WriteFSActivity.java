package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WriteFSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_f_s);
    }

    public void clickCancelFS(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickSaveFS(View view) {
    }
}
