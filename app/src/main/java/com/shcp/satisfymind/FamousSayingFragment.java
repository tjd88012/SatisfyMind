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
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FamousSayingFragment extends Fragment {

    FloatingActionButton button;
    Button listItemButton;
    Context context;
    RecyclerView recyclerView;

    ArrayList<FSListItem> fsList=new ArrayList<>();
    FSListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fs,container,false);

        context=getContext();
        button=view.findViewById(R.id.float_fs);
        listItemButton=view.findViewById(R.id.fslistitem);

        recyclerView=view.findViewById(R.id.recycler_fs);
        adapter=new FSListAdapter(context, fsList);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WriteFSActivity.class);
                startActivity(intent);
            }
        });

        //임시로 화면이 넘어가는 기능 추가
        listItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,FSListItemActivity.class);
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
        Call<ArrayList<FSListItem>> call=retrofitService.loadFS();
        call.enqueue(new Callback<ArrayList<FSListItem>>() {
            @Override
            public void onResponse(Call<ArrayList<FSListItem>> call, Response<ArrayList<FSListItem>> response) {
                ArrayList<FSListItem> items=response.body();

                fsList.clear();
                adapter.notifyDataSetChanged();

                for (FSListItem item:items){
                    fsList.add(0,item);
                    adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FSListItem>> call, Throwable t) {

            }
        });

        //리사이클러뷰는 정상적으로 작동되지만 서버에서 읽어으는 부분이 안됨
        //        //스튜디오 코드와 예제 참조해서 해결해보기

    }
}
