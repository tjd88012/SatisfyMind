package com.shcp.satisfymind;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    EditText tvNickName;
    RadioGroup radioGroup;
    RadioButton man;
    RadioButton woman;
    EditText tvAge;
    Button loginButton;
    static String nickname;
    static String sex;
    String age;
    String serverUrl;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvNickName=findViewById(R.id.et_nickname);
        tvAge=findViewById(R.id.et_age);
        radioGroup=findViewById(R.id.rg);
        man=findViewById(R.id.rb_man);
        woman=findViewById(R.id.rb_woman);
        loginButton=findViewById(R.id.loginbutton);

    }

    public void clickStart(View view) {
        //2. 추가로 로그인 액티비티 화면이 로그인이 된 후 앱을 삭제하기 전에는 MainActivity로 넘어가도록!!--오후에 고민해보고...

        nickname=tvNickName.getText().toString();
        if (nickname.equals("")){
            nickname=null;
        }
        age=tvAge.getText().toString();
        serverUrl="http://shcp.dothome.co.kr/login/login.php";
        sex="";
        if (radioGroup.getCheckedRadioButtonId()==R.id.rb_man){
            sex=man.getText().toString();
        }else if (radioGroup.getCheckedRadioButtonId()==R.id.rb_woman){
            sex=woman.getText().toString();
        }else if (radioGroup.getCheckedRadioButtonId()!=R.id.rb_man
                &&radioGroup.getCheckedRadioButtonId()!=R.id.rb_woman){
            sex=null;
        }

        if (tvNickName.getText().toString().equals("")||tvAge.getText().toString().equals("")
                ||!man.isChecked()&&!woman.isChecked()){
            new AlertDialog.Builder(this).setMessage("모든 항목을 작성해야 앱을 사용할 수 있습니다.")
                    .setPositiveButton("확인", null).create().show();
        } else {

            insertLoginData();

            preferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Nickname", nickname);
            if (man.isChecked()) editor.putString("Sex", man.getText().toString());
            else editor.putString("Sex", woman.getText().toString());
            editor.putString("Age", age);
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void insertLoginData(){
        new Thread(){
            @Override
            public void run() {

                try {

                    nickname= URLEncoder.encode(nickname,"utf-8");
                    sex=URLEncoder.encode(sex,"utf-8");
                    age=URLEncoder.encode(age,"utf-8");

                    URL url=new URL(serverUrl);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    String query = "nickname="+nickname+"&sex="+sex+"&age="+age;

                    OutputStream outputStream=connection.getOutputStream();
                    outputStream.write(query.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    InputStream is=connection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader reader=new BufferedReader(isr);

                    final StringBuffer buffer=new StringBuffer();
                    while (true){
                        String line=reader.readLine();
                        if (line==null) break;
                        buffer.append(line+"\n");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        tvNickName.setText(preferences.getString("Nickname", ""));
        tvAge.setText(preferences.getString("Age", ""));
        if (preferences.getString("Sex","").equals("남")) man.setChecked(true);
        else if (preferences.getString("Sex","").equals("여"))woman.setChecked(true);
        else radioGroup.clearCheck();
        loginButton.performClick();
    }


}
