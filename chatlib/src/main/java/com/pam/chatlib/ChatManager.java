package com.pam.chatlib;

import androidx.recyclerview.widget.RecyclerView;

import com.pam.chatlib.model.MessageModel;

import java.util.List;

public class ChatManager {
    private RecyclerView recycler;
    private int reciverId;
    private int senderId;

    private int textId;
    private int imageId;
    private int progressId;
    private int textTimeId;

    private List<MessageModel> messageList;

    ChatManager(RecyclerView recycler, int reciverId, int senderId, int textId, int imageId, int progressId, int textTimeId,List<MessageModel> messageList) {
        this.recycler = recycler;
        this.reciverId = reciverId;
        this.senderId = senderId;
        this.textId = textId;
        this.imageId = imageId;
        this.progressId = progressId;
        this.textTimeId = textTimeId;
        this.messageList = messageList;
    }

    public static Builder Builder(){
        return new Builder();
    }

    void init(){
        // adapter
        // show data in resycler

    }

    public void sendMessage(MessageModel messageModel){

    }

}
