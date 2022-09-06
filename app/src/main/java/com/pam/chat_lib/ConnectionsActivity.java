package com.pam.chat_lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pam.chatlib.ChatManager;
import com.pam.chatlib.interfaces.AdapterClickListener;
import com.pam.chatlib.models.Connection;

import java.io.Serializable;

public class ConnectionsActivity extends AppCompatActivity {

    private String token;
    ChatManager chatManager;
    RecyclerView ConnectionsRecycler;

    FloatingActionButton floatingButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        chatManager = ((MyApp) this.getApplication()).getChatManager();

        ConnectionsRecycler = findViewById(R.id.recyclerList);
        floatingButton = findViewById(R.id.floatingActionButton);


        token = getIntent().getExtras().getString("token");
        chatManager.setCurrentUserToken(token);

        chatManager.setConnectionRecycler(ConnectionsRecycler);

        chatManager.fitchConnections(new AdapterClickListener() {
            @Override
            public void onItemClick(int pos, Object item, View view) {

                Toast.makeText(ConnectionsActivity.this, pos +" Click", Toast.LENGTH_SHORT).show();

                Intent usersActivity = new Intent(ConnectionsActivity.this,ChatActivity.class);
                usersActivity.putExtra("item", (Serializable) item);
                startActivity(usersActivity);
            }

            @Override
            public boolean onLongItemClick(int pos, Object item, View view) {
                Toast.makeText(ConnectionsActivity.this, pos +" LongClick", Toast.LENGTH_SHORT).show();

                return false;
            }

        });
        floatingButton.setVisibility(View.VISIBLE);
        floatingButton.setOnClickListener(view -> {
            Intent usersActivity = new Intent(this,UsersActivity.class);
            startActivity(usersActivity);
        });


    }
}