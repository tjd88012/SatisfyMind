package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HPListItemActivity extends AppCompatActivity {

    TextView tvNickName;
    ImageView ivPhoto;
    TextView tvText;
    TextView tvFavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_p_list_item);

        tvNickName=findViewById(R.id.item_hp_nickname);
        ivPhoto=findViewById(R.id.item_hp_iv);
        tvText=findViewById(R.id.item_hp_text);
        tvFavor=findViewById(R.id.hp_recommend_count);

        Intent intent=getIntent();
        String nickname=intent.getStringExtra("nickname");
        String file=intent.getStringExtra("file");
        String text=intent.getStringExtra("text");
        int favor=intent.getIntExtra("favor",0);

        tvNickName.setText(nickname);
        Glide.with(this).load(file).into(ivPhoto);
        tvText.setText(text);
        tvFavor.setText(favor+"");

    }

    public void clickToggleHP(View view) {
    }

    public void clickSaveReplyHP(View view) {
    }
}
