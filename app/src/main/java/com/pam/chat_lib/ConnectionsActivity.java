package com.pam.chat_lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pam.chatlib.ChatManager;

public class ConnectionsActivity extends AppCompatActivity {

    private String token;
    ChatManager chatManager = ((MyApp) this.getApplication()).getChatManager();
    RecyclerView ConnectionsRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ConnectionsRecycler = findViewById(R.id.recyclerList);

        token = getIntent().getExtras().getString("token");

        chatManager.setConnectionRecycler(ConnectionsRecycler);

        chatManager.fitchConnections();


    }
}