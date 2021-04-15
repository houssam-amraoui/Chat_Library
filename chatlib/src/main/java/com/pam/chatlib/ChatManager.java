package com.pam.chatlib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pam.chatlib.adapter.ChatAdapterV2;
import com.pam.chatlib.model.MessageModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatManager {
    private RecyclerView recycler;
    private int reciverId;
    private int senderId;

    private int textId;
    private int imageId;
    private int progressId;
    private int textTimeId;
    private String currentUser;
    private String child;
    private String childAdd;
    DatabaseReference databaseReference;



    ChatManager(RecyclerView recycler, int reciverId, int senderId, int textId, int imageId, int progressId, int textTimeId,String currentUser,DatabaseReference reference) {
        this.recycler = recycler;
        this.reciverId = reciverId;
        this.senderId = senderId;
        this.textId = textId;
        this.imageId = imageId;
        this.progressId = progressId;
        this.textTimeId = textTimeId;
        this.currentUser=currentUser;
        this.databaseReference=reference;

    }

    public static Builder Builder(){
        return new Builder();
    }

    void init(){
        ChatAdapterV2 adapter = new ChatAdapterV2(recycler.getContext(),currentUser,senderId);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
        recycler.setAdapter(adapter);

    }
    void updateChanges(MessageModel messageModel){
        DatabaseReference database=FirebaseDatabase.getInstance().getReference();
        database.child(child).child(childAdd);
        HashMap<String,MessageModel> messageModelHashMap= new HashMap<>();
    }
    void sendMessageFirstTime(){
    }

    public void sendMessage(MessageModel messageModel){

    }

    public void addRoom(){
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("name","kecheama");
        DatabaseReference ref =databaseReference.child("chat").push();
        String a = ref.getKey();
        ref.setValue(childUpdates);

    }

}
