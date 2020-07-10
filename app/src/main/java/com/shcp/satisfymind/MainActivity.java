package com.shcp.satisfymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView tvTitle;

    Fragment[] fragments=new Fragment[3];
    FragmentManager manager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navi_open,R.string.navi_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        manager=getSupportFragmentManager();
        fragments[0]=new FamousSayingFragment();
        fragments[1]=new HealingPhotoFragment();
        fragments[2]=new WorryFragment();

        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.view, fragments[0]);
        transaction.commit();

        bottomNavigationView=findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction tran=manager.beginTransaction();
                tvTitle=findViewById(R.id.tv_title);

                switch(menuItem.getItemId()){
                    case R.id.menu_fs:
                        tvTitle.setText("명언모음집");
                        tran.replace(R.id.view,fragments[0]);
                        break;

                    case R.id.menu_hp:
                        tvTitle.setText("힐링포토");
                        tran.replace(R.id.view,fragments[1]);
                        break;

                    case R.id.menu_worry:
                        tvTitle.setText("고민게시판");
                        tran.replace(R.id.view,fragments[2]);
                        break;
                }
                tran.commit();

                return true;
            }
        });

        navigationView=findViewById(R.id.navi);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.diary:
                        Intent intent1=new Intent(MainActivity.this,MyDiaryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.camera:
                        Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent2);
                        break;
                }

                drawerLayout.closeDrawer(navigationView);

                return false;
            }
        });



    }
}
