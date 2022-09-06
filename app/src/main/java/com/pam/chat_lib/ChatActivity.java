package com.pam.chat_lib;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.chatlib.ChatManager;
import com.pam.chatlib.models.Connection;
import com.pam.chatlib.models.User;
import com.vanniktech.emoji.EmojiEditText;

import java.io.Serializable;

public class ChatActivity extends AppCompatActivity {
    RecyclerView chatRecycler;
    ImageButton sendBtn;
    //int user;
    ChatManager chatManager;
    EmojiEditText message;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatRecycler = findViewById(R.id.listMessages);
        sendBtn = findViewById(R.id.sendMessageButton);
        message = findViewById(R.id.MessageWrapper);

        Bundle bundle =  getIntent().getExtras();

        chatManager = ((MyApp) this.getApplication()).getChatManager();
        chatManager.setChatRecycler(chatRecycler);
        Serializable item =bundle.getSerializable("item");

        if (item instanceof Connection) {
            Connection connection = (Connection) item;
            chatManager.fitchMessages(connection.getRoom_id());

        }







        sendBtn.setOnClickListener(v -> {



           // Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show();

          //  MessageModel mm = new MessageModel();
         //   mm.setChat_id("dsdfsdf");
            chatManager.sendMessage(message.getText().toString(),item);




            //chatManager.testMessage();


        });

    }
}
