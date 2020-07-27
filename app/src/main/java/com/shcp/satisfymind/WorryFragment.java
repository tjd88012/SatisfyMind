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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorryFragment extends Fragment {

    FloatingActionButton button;
    Button listItemButton;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_worry,container,false);

        context=getContext();
        button=view.findViewById(R.id.float_worry);
        listItemButton=view.findViewById(R.id.worrylistitem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WriteWorryActivity.class);
                startActivity(intent);
            }
        });

        //임시로 화면이 넘어가는 기능 추가
        listItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WorryListItemActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
