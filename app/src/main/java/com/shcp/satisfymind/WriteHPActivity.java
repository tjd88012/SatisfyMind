package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WriteHPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_h_p);
    }

    public void clickCancelHP(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickSaveHP(View view) {
    }
}
