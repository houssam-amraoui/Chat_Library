package com.pam.chat_lib;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.pam.chatlib.ChatManager;
import com.pam.chatlib.models.ItemConversationIds;
import com.pam.chatlib.models.ItemMessageIds;
import com.pam.chatlib.models.ItemUserIds;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.twitter.TwitterEmojiProvider;

public class MyApp extends Application {
    private ChatManager chatManager;

    @Override
    public void onCreate() {
        super.onCreate();
        EmojiManager.install(new TwitterEmojiProvider());

        chatManager = ChatManager.Builder()
                .setCurrentUserToken("1")
                .setDataReference(FirebaseDatabase.getInstance().getReference())
                .setItemUserIds(new ItemUserIds())
                .setItemConversationIds(new ItemConversationIds(R.layout.item_conversation, R.id.root,R.id.image,R.id.title,R.id.countContainer,R.id.countBadge,R.id.iconBadge,R.id.message,R.id.favourite,R.id.peopleNearby_personStatus))
                .setItemMessageIds(new ItemMessageIds(R.layout.item_message_received,R.layout.item_message_sent, R.id.text_message_body, R.id.text_message_time, R.id.status_messages,R.id.chatMessage_photo_loading, R.id.image_lyt,R.id.chatMessage_photo_image))

                .build();
    }

    public ChatManager getChatManager() {
        return chatManager;
    }
}
