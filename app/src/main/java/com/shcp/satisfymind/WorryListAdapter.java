package com.shcp.satisfymind;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorryListAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<WorryListItem> worryList;
    SharedPreferences preferences;

    public WorryListAdapter() {
    }

    public WorryListAdapter(Context context, ArrayList<WorryListItem> worryList) {
        this.context = context;
        this.worryList = worryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        preferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
        View view=null;
        if (preferences.getString("Sex","").equals("남")) {
            view = inflater.inflate(R.layout.worry_man, parent, false);
        }else{
            view=inflater.inflate(R.layout.worry_woman,parent,false);
        }
        VH holder=new VH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;
        WorryListItem listItem=worryList.get(position);

        vh.title.setText(listItem.title);
        vh.countFavor.setText(listItem.favor+"");
    }

    @Override
    public int getItemCount() {
        return worryList.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView title;
        TextView countFavor;

        public VH(@NonNull View itemView) {
            super(itemView);

            //임시로 남자 고민으로 통일
            if (preferences.getString("Sex","").equals("남")) {
                title=itemView.findViewById(R.id.worry_list_title_man);
                countFavor=itemView.findViewById(R.id.worry_list_count_man);
            }else if (preferences.getString("Sex","").equals("여")){
                title=itemView.findViewById(R.id.worry_list_title_woman);
                countFavor=itemView.findViewById(R.id.worry_list_count_woman);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WorryListItem listItem=worryList.get(getLayoutPosition());
                    Intent intent=new Intent(context,WorryListItemActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }

}
