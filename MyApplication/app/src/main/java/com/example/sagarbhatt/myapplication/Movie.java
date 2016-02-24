package com.example.sagarbhatt.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class Movie extends Activity {

    String itemName[] = {"DDLJ","JTHJ"};
    Integer imgid[]={R.drawable.ddlj,R.drawable.jab};
    ListView listn;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CustomListAdapter adapter = new CustomListAdapter(this,itemName,imgid);
        listn=(ListView)findViewById(R.id.list1);
        listn.setAdapter(adapter);

        listn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Slecteditem= itemName[+position];

                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        btn=(Button)findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Movie.this, content.class);
                startActivity(i);
            }
        });

    }

}
