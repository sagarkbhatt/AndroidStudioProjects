package com.example.sagarbhatt.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Firebase FireRef;
    private ListView chatLis;
     FirebaseListAdapter<ChatMessage> mListAdapter ;
    Button login;

    public MainActivity() {
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        mListAdapter.cleanup();
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        chatLis = (ListView)this.findViewById(android.R.id.list);

        FireRef = new Firebase("https://fiery-heat-2572.firebaseio.com/chat");

        final ListView listView = (ListView) this.findViewById(android.R.id.list);
        mListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,android.R.layout.two_line_list_item, FireRef) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView) v.findViewById(android.R.id.text2)).setText(model.getMessage());
            }
        };
        listView.setAdapter(mListAdapter);

        final EditText msgSend = (EditText) findViewById(R.id.txtEdit);

        Button BtnSend=(Button)findViewById(R.id.btnSend);
        BtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = msgSend.getText().toString();
                ChatMessage message = new ChatMessage("Android User", text);
                FireRef.push().setValue(message);
                msgSend.setText("");
            }
        });



    }

}
