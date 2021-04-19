package com.pam.chatlib;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.chatlib.model.MessageModel;

import java.util.List;

public class Builder{

    private RecyclerView recycler;
    private int layoutReciverId;
    private int layoutSenderId;

    private int textId;
    private int imageId;
    private int progressId;
    private int textTimeId;
    private DatabaseReference databaseReference;
    private String CurrentUserId;

    public Builder setRecycler(RecyclerView recycler) {
        this.recycler = recycler;
        return this;
    }

    public Builder setReciverItem(int layoutReciverId) {
        this.layoutReciverId = layoutReciverId;
        return this;
    }

    public Builder setSenderItem(int layoutSenderId) {
        this.layoutSenderId = layoutSenderId;
        return this;
    }
    public Builder setComponentsIdis(int textId,int imageId,int progressId,int textTimeId) {
        this.textId = textId;
        this.imageId = imageId;
        this.progressId = progressId;
        this.textTimeId = textTimeId;
        return this;
    }
    

    public Builder setDataReference(DatabaseReference dataReference) {
            this.databaseReference=dataReference;
        return this;
    }
    public ChatManager build() {
        // TODO: 04/04/2021 add throw exption 

        ChatManager chatManager = new ChatManager(recycler,layoutReciverId,layoutSenderId,textId,imageId,progressId,textTimeId,CurrentUserId,databaseReference);
        //chatManager.init();
        return chatManager;
    }

    /*public Builder setUserId(int userId) {
        return this;
    }*/
}