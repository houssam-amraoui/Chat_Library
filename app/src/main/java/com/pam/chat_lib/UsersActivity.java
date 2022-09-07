package com.pam.chat_lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pam.chatlib.ChatManager;
import com.pam.chatlib.interfaces.AdapterClickListener;
import com.pam.chatlib.models.Connection;
import com.pam.chatlib.models.User;

import java.io.Serializable;

public class UsersActivity extends AppCompatActivity {

    ChatManager chatManager;

    RecyclerView usersRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatManager = ((MyApp) this.getApplication()).getChatManager();
        setContentView(R.layout.activity_list);
        usersRecycler = findViewById(R.id.recyclerList);

        chatManager.setUserRecycler(usersRecycler);

        chatManager.fitchUsers(new AdapterClickListener() {
            @Override
            public void onItemClick(int pos, Object item, View view) {
                Intent usersActivity = new Intent(UsersActivity.this,ChatActivity.class);
                usersActivity.putExtra("item",(Serializable)item);
                startActivity(usersActivity);
            }

            @Override
            public boolean onLongItemClick(int pos, Object item, View view) {

                return false;
            }


        });



    }
}