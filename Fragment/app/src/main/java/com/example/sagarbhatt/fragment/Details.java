package com.example.sagarbhatt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sagar Bhatt on 22-02-2016.
 */
public class Details extends AppCompatActivity{
    TextView PName,PRun;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_fragment);
        Intent j=getIntent();
        PName=(TextView)findViewById(R.id.PlayerName);
        PRun=(TextView)findViewById(R.id.TotalRun);
        PName.setText(j.getExtras().getString("PlayerName"));
        PRun.setText(j.getExtras().getString("PlayerRun"));
    }
}
