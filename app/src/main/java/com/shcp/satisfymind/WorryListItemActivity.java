package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WorryListItemActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvText;
    TextView tvNickName;
    TextView tvFavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worry_list_item);

        tvTitle=findViewById(R.id.item_worry_title);
        tvText=findViewById(R.id.item_worry_text);
        tvNickName=findViewById(R.id.item_worry_nickname);
        tvFavor=findViewById(R.id.worry_recommend_count);

        Intent intent=getIntent();
        int no=intent.getIntExtra("no",0);
        String title=intent.getStringExtra("title");
        String text=intent.getStringExtra("text");
        String nickname=intent.getStringExtra("nickname");
        int favor=intent.getIntExtra("favor",0);

        tvTitle.setText(title);
        tvText.setText(text);
        tvNickName.setText(nickname);
        tvFavor.setText(favor+"");


    }

    public void clickSaveReplyWorry(View view) {
    }

    public void clickToggleWorry(View view) {
    }
}
