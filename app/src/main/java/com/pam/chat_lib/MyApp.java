package com.pam.chat_lib;

import android.app.Application;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.twitter.TwitterEmojiProvider;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EmojiManager.install(new TwitterEmojiProvider());
    }

}
