package com.example.sagarbhatt.testdb;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Updateplayer extends AppCompatActivity implements
        AdapterView.OnItemClickListener,View.OnClickListener {

    ListView lv1;
    ListAdapter la1;
    Button b1;
    EditText e1;
    List<String> players=new ArrayList<String>();;
    List<Integer> playerid=new ArrayList<Integer>();
    int updateIndex;
    String updateplayer;
    String selectdplayer;
    DBHelper dhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateplayer);
        b1 = (Button)findViewById(R.id.listupdatebut);
        e1 = (EditText)findViewById(R.id.playeredit);
        lv1 = (ListView)findViewById(R.id.updatelist);
        b1.setOnClickListener(this);
        dhelp = new DBHelper(this);
        dhelp.open();
        Cursor c2 = dhelp.getAllEntries();
        c2.moveToFirst();
        for(int i =0;i<c2.getCount();i++)
        {
            playerid.add(c2.getInt(0));
            players.add(c2.getString(1));
            c2.moveToNext();
        }
        dhelp.close();
        la1 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,players);
        lv1.setAdapter(la1);

        lv1.setOnItemClickListener(this);
        Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        updateIndex = playerid.get(i);
        updateplayer = players.get(i);
        e1.setText(updateplayer);
        Toast.makeText(this,""+updateIndex,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
        String player = e1.getText().toString();
        if(player==null||player.length()==0||player.equals(""))
        {
            Toast.makeText(this, "Re Enter", Toast.LENGTH_LONG).show();
        }
        else
        {
            DBHelper dh = new DBHelper(this);
            dh.open();
            // Toast.makeText(this,""+updateIndex,Toast.LENGTH_SHORT).show();
            //selectdplayer = players.get(playerid.get(updateIndex));
            dh.updateEntry(updateIndex,e1.getText().toString());

            dh.close();
            finish();
        }
    };
}
