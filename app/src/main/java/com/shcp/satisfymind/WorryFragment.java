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

public class WorryFragment extends Fragment {

    FloatingActionButton button;
    Context context;

    RecyclerView recyclerView;
    ArrayList<WorryListItem> worryList=new ArrayList<>();
    WorryListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_worry,container,false);

        context=getContext();
        button=view.findViewById(R.id.float_worry);

        recyclerView=view.findViewById(R.id.recycler_worry);
        adapter=new WorryListAdapter(context, worryList);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WriteWorryActivity.class);
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
        Call<ArrayList<WorryListItem>> call = retrofitService.loadWorry();
        call.enqueue(new Callback<ArrayList<WorryListItem>>() {
            @Override
            public void onResponse(Call<ArrayList<WorryListItem>> call, Response<ArrayList<WorryListItem>> response) {
                ArrayList<WorryListItem> items = response.body();

                worryList.clear();
                adapter.notifyDataSetChanged();

                for (WorryListItem item:items){
                    worryList.add(0,item);
                    adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WorryListItem>> call, Throwable t) {

            }
        });

    }
}
