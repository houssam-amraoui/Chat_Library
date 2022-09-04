package com.pam.chat_lib;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.chatlib.ChatManager;
import com.vanniktech.emoji.EmojiEditText;

public class ChatActivity extends AppCompatActivity {
    RecyclerView chatRecycler;
    ImageButton sendBtn;
    //int user;
    ChatManager chatManager;
    EmojiEditText message;

    MyApp myApp = ((MyApp) this.getApplication());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatRecycler = findViewById(R.id.listMessages);
        sendBtn = findViewById(R.id.sendMessageButton);
        message = findViewById(R.id.MessageWrapper);


        chatManager = myApp.getChatManager();
        chatManager.setChatRecycler(chatRecycler);

        chatManager.fitchMessages();



        sendBtn.setOnClickListener(v -> {



           // Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show();

          //  MessageModel mm = new MessageModel();
         //   mm.setChat_id("dsdfsdf");
            chatManager.sendMessage();

            //chatManager.testMessage();


        });

    }
}
