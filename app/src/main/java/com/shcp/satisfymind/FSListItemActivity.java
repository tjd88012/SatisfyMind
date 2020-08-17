package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FSListItemActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvNickName;
    TextView tvText;
    TextView tvFavor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_s_list_item);

        tvTitle=findViewById(R.id.item_fs_title);
        tvNickName=findViewById(R.id.item_fs_nickname);
        tvText=findViewById(R.id.item_fs_text);
        tvFavor=findViewById(R.id.fs_recommend_count);

        Intent intent=getIntent();
        int no=intent.getIntExtra("no",0);
        String title = intent.getStringExtra("title");
        String nickname = intent.getStringExtra("nickname");
        String text = intent.getStringExtra("text");
        int favor = intent.getIntExtra("favor",0);

        tvTitle.setText(title);
        tvNickName.setText(nickname);
        tvText.setText(text);
        tvFavor.setText(favor+"");

    }

    public void clickSaveReplyFS(View view) {
    }

    public void clickToggleFS(View view) {
    }
}
