package com.shcp.satisfymind;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


public class MyDiaryActivity extends AppCompatActivity {

    ListView listView;
    DiaryAdapter adapter;
    DiaryVO diaryVO;
    String tvDiary;
    String num;
    String tvDate;
    Cursor cursor;
    ArrayList<DiaryVO> diarys=new ArrayList<>();
    SQLiteDatabase database;
    String tableName="diary";
    String diaryDB="diary.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        listView=findViewById(R.id.diary_listview);

        //tvDiary=data.getStringExtra("diary_text");
        //tvDate=data.getStringExtra("diary_date");
        database=openOrCreateDatabase(diaryDB,MODE_PRIVATE, null);
        try {
            cursor=database.rawQuery("SELECT * FROM "+tableName,null);

            while (cursor.moveToNext()){
                num=cursor.getString(cursor.getColumnIndex("Num"));
                tvDiary=cursor.getString(cursor.getColumnIndex("Diary"));
                tvDate=cursor.getString(cursor.getColumnIndex("Date"));
                if (tvDiary!=null&&tvDiary!="") {
                    diaryVO = new DiaryVO(num,tvDiary, tvDate);
                    diarys.add(0,diaryVO);
                }
            }

        }catch (Exception e){}

        adapter=new DiaryAdapter(diarys,getLayoutInflater());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MyDiaryActivity.this);
                builder.setTitle("다이어리 삭제");
                builder.setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Num=diarys.get(position).getNum();
                        database.execSQL("DELETE FROM "+tableName+" WHERE Num=?",new String[]{Num});
                        diarys.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();

                return true;
            }
        });
    }

    public void clickDiaryWrite(View view) {
        Intent intent = new Intent(this,WriteDiaryActivity.class);
        startActivity(intent);
        finish();
    }

}
