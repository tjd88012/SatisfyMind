package com.shcp.satisfymind;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WriteHPActivity extends AppCompatActivity {

    ImageView ivPhoto;
    EditText etText;
    TextView tvNickName;

    final int PERMISSION_CODE=100;
    final int PHOTO_UPLOAD=10;

    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_h_p);

        ivPhoto=findViewById(R.id.write_photo_iv);
        etText=findViewById(R.id.write_photo_text_et);
        tvNickName=findViewById(R.id.write_photo_nickname);

        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        tvNickName.setText(preferences.getString("Nickname",""));

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:
                if (grantResults[0]==PackageManager.PERMISSION_DENIED){
                    new AlertDialog.Builder(this).setMessage("사진파일 전송이 불가합니다").setPositiveButton("확인",null).create().show();
                }
                break;
        }
    }

    public void clickPhotoUpload(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PHOTO_UPLOAD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_UPLOAD:
                if (resultCode==RESULT_OK){
                    Uri uri=data.getData();
                    if (uri!=null)
                    Glide.with(this).load(uri).into(ivPhoto);
                    imagePath=getRealPathFromUri(uri);
                }
                break;
        }
    }

    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    public void clickCancelHP(View view) {
        finish();
    }

    public void clickSaveHP(View view) {

        String nickname=tvNickName.getText().toString();
        String text=etText.getText().toString();
        if (text.equals("")){
            text=null;
        }

        Retrofit retrofit=RetrofitHelper.getInstanceInsert();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);

        Map<String, String> item=new HashMap<>();

        item.put("Nickname",nickname);
        item.put("Text",text);

        MultipartBody.Part file=null;
        if (imagePath!=null){
            File f=new File(imagePath);
            RequestBody requestBody=RequestBody.create(MediaType.parse("image/*"),f);
            file=MultipartBody.Part.createFormData("Image",f.getName(),requestBody);
        }

        Call<String> call = retrofitService.insertHP(item,file);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (item.get("Text").equals(null)){
                    new AlertDialog.Builder(WriteHPActivity.this).setMessage("모든 항목 입력 후 업로드가 가능합니다.")
                            .setPositiveButton("확인",null).create().show();
                }else {
                    Toast.makeText(WriteHPActivity.this, "정상적으로 업로드되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
