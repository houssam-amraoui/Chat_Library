package com.pam.chat_lib;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.chatlib.ChatManager;
import com.pam.chatlib.model.MessageModel;
import com.vanniktech.emoji.EmojiEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    RecyclerView chat;
    ImageButton sendBtn;
    //int user;
    ChatManager chatManager;
    EmojiEditText message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chat = findViewById(R.id.listMessages);
        sendBtn = findViewById(R.id.sendMessageButton);
        message=findViewById(R.id.MessageWrapper);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();



        chatManager = ChatManager.Builder()
                .setRecycler(chat)
                .setReciverItem(R.layout.item_message_received)
                .setSenderItem(R.layout.item_message_sent)
                .setDataReference(database)
                 //.setUserId(user);
                //
                .build();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MessageModel mm = new MessageModel();
               // mm.setChat_id("dsdfsdf");
               chatManager.sendMessage();


            }
        });

    }
}
