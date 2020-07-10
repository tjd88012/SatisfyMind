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

    public void clickPhotoUpload(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //url을 가져와야 하는 ForResult써야하지만 임시로 넘어가는지만 확인
        startActivity(intent);
    }

    public void clickCancelHP(View view) {
        finish();
    }

    public void clickSaveHP(View view) {
    }
}
