package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickStart(View view) {

        //닉네임, 성별, 나이가 모두 입력이 완료된 후 MainActivity로 넘어감
        //개인 서버와 연동하여 입력한 정보를 Retrofit 의 POST방식으로 서버와 연결
        //화면이 원할하게 작동하는지 임시로 MainActivity로 넘어감

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
