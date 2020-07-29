package com.shcp.satisfymind;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HealingPhotoFragment extends Fragment {

    FloatingActionButton button;
    Button listItemButton;
    Context context;

    RecyclerView recyclerView;
    ArrayList<HPListItem> hpList=new ArrayList<>();
    HPListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_hp,container,false);

        context=getContext();
        button=view.findViewById(R.id.float_hp);
        listItemButton=view.findViewById(R.id.hplistitem);

        recyclerView=view.findViewById(R.id.recycler_hp);
        adapter=new HPListAdapter(context,hpList);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WriteHPActivity.class);
                startActivity(intent);
            }
        });

        //임시로 화면이 넘어가는 기능 추가
        listItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,HPListItemActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Retrofit retrofit=RetrofitHelper.getInstanceLoad();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
        Call<ArrayList<HPListItem>> call = retrofitService.loadHP();
        call.enqueue(new Callback<ArrayList<HPListItem>>() {
            @Override
            public void onResponse(Call<ArrayList<HPListItem>> call, Response<ArrayList<HPListItem>> response) {
                ArrayList<HPListItem> items=response.body();

                hpList.clear();
                adapter.notifyDataSetChanged();

                for (HPListItem item:items){
                    hpList.add(0,item);
                    adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HPListItem>> call, Throwable t) {

            }
        });


    }
}
