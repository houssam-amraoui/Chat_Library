package com.pam.chat_lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pam.chatlib.ChatManager;
import com.pam.chatlib.interfaces.AdapterClickListener;

public class UsersActivity extends AppCompatActivity {

    private String token;
    ChatManager chatManager;


    RecyclerView usersRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatManager = ((MyApp) this.getApplication()).getChatManager();
        setContentView(R.layout.activity_list);
        usersRecycler = findViewById(R.id.recyclerList);

        token = getIntent().getExtras().getString("token");
        chatManager.setUserRecycler(usersRecycler);
        chatManager.setCurrentUserToken(token);

        chatManager.fitchUsers(new AdapterClickListener() {
            @Override
            public void onItemClick(int pos, Object item, View view) {
                Toast.makeText(UsersActivity.this, pos +" Click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongItemClick(int pos, Object item, View view) {
                Toast.makeText(UsersActivity.this, pos +" LongClick", Toast.LENGTH_SHORT).show();

                return false;
            }


        });



    }
}