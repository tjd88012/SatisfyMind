package com.shcp.satisfymind;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WriteFSActivity extends AppCompatActivity {

    EditText etTitle, etText;
    TextView tvNickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_f_s);

        etTitle=findViewById(R.id.write_fs_title_et);
        etText=findViewById(R.id.write_fs_text_et);
        tvNickName=findViewById(R.id.write_fs_nickname);

        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        tvNickName.setText(preferences.getString("Nickname",""));
    }

    public void clickCancelFS(View view) {
        finish();
    }

    public void clickSaveFS(View view) {
        String title=etTitle.getText().toString();
        if (title.equals("")){
            title=null;
        }
        String text=etText.getText().toString();
        if (text.equals("")){
            text=null;
        }
        String nickname=tvNickName.getText().toString();

        Retrofit retrofit=RetrofitHelper.getInstanceInsert();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);

        Map<String, String> item=new HashMap<>();

        item.put("Title",title);
        item.put("Text",text);
        item.put("Nickname",nickname);

        Call<String> call = retrofitService.insertFS(item);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (item.get("Title").equals(null)||item.get("Text").equals(null)){
                    new AlertDialog.Builder(WriteFSActivity.this).setMessage("모든 항목 입력 후 업로드가 가능합니다.")
                            .setPositiveButton("확인",null).create().show();
                }else{
                    Toast.makeText(WriteFSActivity.this, "정상적으로 업로드되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }
}
