package com.example.sagarbhatt.testdb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //DBHelper db;
    EditText editName;
    Button btnSubmit;
    Button btnShow;
    Button btnUpdate;
    ListView lv;
    private List<String> myList;
    private List<Integer> idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,Updateplayer.class);
                startActivity(i);
            }
        });

        editName= (EditText) findViewById(R.id.etName);
        btnSubmit= (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = editName.getText().toString();
                if(temp.equals("")||temp==null||temp.length()==0)
                {
                    Toast.makeText(MainActivity.this, "Re Enter", Toast.LENGTH_LONG).show();
                }
                else
                {
                    DBHelper addb1 = new DBHelper(MainActivity.this);
                    addb1.open();
                    addb1.insertEntry(editName.getText().toString());
                    addb1.close();
                    Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_LONG).show();
                    //finish();

                }

            }
        });


    btnShow= (Button) findViewById(R.id.btnShow);

    btnShow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ListAdapter la;
            DBHelper dhelper;
            lv = (ListView)findViewById(R.id.listName);
            myList = new ArrayList<String>();
            idList = new ArrayList<Integer>();
            dhelper = new DBHelper(MainActivity.this);
            dhelper.open();
            Cursor c = dhelper.getAllEntries();
            c.moveToFirst();
            for (int i =0; i<c.getCount();i++)
            {
                idList.add(c.getInt(0));
                myList.add(c.getString(1));
                c.moveToNext();
            }
            c.close();
            la = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,myList);
            lv.setAdapter(la);
            lv.setOnItemClickListener(MainActivity.this);


        }
    });



    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       final DBHelper dhelper;
        final int indexvalue = idList.get(i);
        dhelper = new DBHelper(this);
        dhelper.open();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Delete");
        dialog.setMessage("Do you want to delete "+ myList.get(i) + " " + indexvalue);
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean result = dhelper.removeEntry(indexvalue);
                dhelper.close();
                onResume();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onResume();
            }
        });

        dialog.create();
        dialog.show();

    }
}
