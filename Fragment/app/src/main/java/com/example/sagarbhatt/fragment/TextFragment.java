package com.example.sagarbhatt.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sagar Bhatt on 22-02-2016.
 */
public class TextFragment extends Fragment {
    private Button txtName,txtRun;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.text_fragment,container,false);
        txtName=(Button)v.findViewById(R.id.PlayerName);
        txtRun=(Button)v.findViewById(R.id.TotalRun);
       txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),Details.class);
                i.putExtra("PlayerName",txtName.getText().toString());
                i.putExtra("PlayerRun",txtRun.getText().toString());
                Log.d(txtName.getText().toString(),"Hello");

                startActivity(i);
            }
        });

        return v;
    }
    public void change(String a,String b){

        txtName.setText(a);
        txtRun.setText(b);
    }




}
