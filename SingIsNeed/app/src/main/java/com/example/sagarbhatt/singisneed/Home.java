package com.example.sagarbhatt.singisneed;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import java.util.Locale;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.widget.Toast;
import java.util.ArrayList;
import java.io.*;
import android.speech.RecognizerIntent;
import android.content.ContentResolver;
import android.database.Cursor;
import java.net.*;
import java.util.List;

class audioFilter implements FilenameFilter{

    @Override
    public boolean accept(File dir,String name){

        //return (name.contains(Home.dataRes) && name.endsWith(".mp3"));

        return ( name.endsWith(".pdf"));

    }
}
public class Home extends AppCompatActivity {

    private TextView txtSpeechInput;
    private ListView lv;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private MediaPlayer mp=new MediaPlayer();
    public static String dataRes=null;
    //private static final String SD_PATH= new String("/sdcard/");

    private final String SD_PATH= Environment.getExternalStorageDirectory().getPath();
    private List<String> songs= new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         lv= (ListView) findViewById(R.id.listView);
        txtSpeechInput=(TextView)findViewById(R.id.txtSpeechIp);
        btnSpeak=(ImageButton)findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                promptSpeechInput();

            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    dataRes=result.get(0);
                    // fetching data from external

                    updatePlayList();

                }else{

                    txtSpeechInput.setText("Sorry,..Could not find");

                }

                }
                break;
            }

        }

    private void updatePlayList() {
        //Toast.makeText(Home.this, "Inside updateList", Toast.LENGTH_SHORT).show();
    File home=new File(SD_PATH);
    FilenameFilter filter=new audioFilter();
    File[] files=home.listFiles(filter);
        Toast.makeText(Home.this,files.toString(), Toast.LENGTH_LONG).show();
        //if(home.listFiles(new audioFilter()).length>0){

            for(File f: files){
                Toast.makeText(Home.this, "Adding"+f.getName(), Toast.LENGTH_SHORT).show();
                songs.add(f.getName());
            }
        Toast.makeText(Home.this, songs.toString(), Toast.LENGTH_SHORT).show();
            ArrayAdapter<String> songList=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,songs);
             lv.setAdapter(songList);
       // }
    }
}


