package com.shcp.satisfymind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HPListAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<HPListItem> hpList;

    public HPListAdapter() {
    }

    public HPListAdapter(Context context, ArrayList<HPListItem> hpList) {
        this.context = context;
        this.hpList = hpList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.healing_photo,parent,false);
        VH holder=new VH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;
        HPListItem item=hpList.get(position);

        String url="ftp://shcp.dothome.co.kr/html/login/HealingPhoto/"+item.file;
        Glide.with(context).load(url).into(vh.imageView);
        vh.text.setText(item.text);
        vh.countFavor.setText(item.favor+"");

    }

    @Override
    public int getItemCount() {
        return hpList.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView text;
        TextView countFavor;

        public VH(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.list_item_hp_iv);
            text=itemView.findViewById(R.id.list_item_tv);
            countFavor=itemView.findViewById(R.id.hp_list_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HPListItem listItem=hpList.get(getLayoutPosition());
                    Intent intent=new Intent(context,HPListItemActivity.class);

                    intent.putExtra("no",listItem.no);
                    intent.putExtra("nickname",listItem.nickname);
                    intent.putExtra("image",listItem.file);
                    intent.putExtra("text",listItem.text);
                    intent.putExtra("favor",listItem.favor);

                    context.startActivity(intent);
                }
            });

        }
    }

}
