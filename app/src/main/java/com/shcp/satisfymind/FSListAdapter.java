package com.shcp.satisfymind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FSListAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<FSListItem> fsItem;

    public FSListAdapter() {
    }

    public FSListAdapter(Context context, ArrayList<FSListItem> fsItem) {
        this.context = context;
        this.fsItem = fsItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.famous_saying,parent,false);
        VH holder=new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH) holder;
        FSListItem item=fsItem.get(position);

        vh.title.setText(item.title);
        vh.countFavor.setText(item.favor+"");
    }

    @Override
    public int getItemCount() {
        return fsItem.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView title;
        TextView countFavor;

        public VH(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.fs_list_title);
            countFavor=itemView.findViewById(R.id.fs_list_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FSListItem listItem=fsItem.get(getLayoutPosition());
                    Intent intent=new Intent(context, FSListItemActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

}
