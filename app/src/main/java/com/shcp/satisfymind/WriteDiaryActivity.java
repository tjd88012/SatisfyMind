package com.shcp.satisfymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteDiaryActivity extends AppCompatActivity {

    EditText tvDiary;
    String diary;
    String date;
    RelativeLayout diaryLayout;
    String diaryDB="diary.db";
    SQLiteDatabase sqLiteDatabase;
    String tableName="diary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        tvDiary=findViewById(R.id.diary_et);
        sqLiteDatabase=openOrCreateDatabase(diaryDB, MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"(Num Integer primary key autoincrement, Diary TEXT, Date TEXT)");
    }

    public void clickSaveDiary(View view) {
        diary=tvDiary.getText().toString();
        date=new SimpleDateFormat("yyyy_MM_dd hh:mm:ss").format(new Date());
        if (diary!=null&&diary!="") sqLiteDatabase.execSQL("INSERT INTO " + tableName + "(Diary, Date) VALUES('" + diary + "', '" + date + "')");
        Intent intent=new Intent(this , MyDiaryActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickCancelDiary(View view) {
        Intent intent=new Intent(this , MyDiaryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this , MyDiaryActivity.class);
        startActivity(intent);
        finish();
    }
}
