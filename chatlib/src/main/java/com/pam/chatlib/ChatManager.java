package com.pam.chatlib;


import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.chatlib.adapter.ChatAdapterV2;
import com.pam.chatlib.model.MessageModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
    private DatabaseReference databaseReference;
    private String roomRefKey;
    private String messageRefKey;


    ChatManager(RecyclerView recycler, int reciverId, int senderId, int textId, int imageId, int progressId, int textTimeId, String currentUser, DatabaseReference reference) {
        this.recycler = recycler;
        this.reciverId = reciverId;
        this.senderId = senderId;
        this.textId = textId;
        this.imageId = imageId;
        this.progressId = progressId;
        this.textTimeId = textTimeId;
        this.currentUser = currentUser;
        this.databaseReference = reference;

    }

    public static Builder Builder() {
        return new Builder();
    }

    void init() {
        ChatAdapterV2 adapter = new ChatAdapterV2(recycler.getContext(), currentUser, senderId);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
        recycler.setAdapter(adapter);

    }

    void updateChanges(MessageModel messageModel) {
    }

    void sendMessageFirstTime() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);
        String time = simpleDateFormat.format(c);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("id_user", "02");
        childUpdates.put("message", "AFin CV");
        childUpdates.put("time", time);
        DatabaseReference ref = databaseReference.child("chat").push();
        DatabaseReference ref2 = ref.push();
        roomRefKey = ref.getKey();
        messageRefKey = ref2.getKey();
        ref2.setValue(childUpdates);
        Log.e("Items", "REF 1 =" + roomRefKey + " and REF 2 =" + messageRefKey);
    }

    public void sendMessage() {
        if (databaseReference.child("chat").getKey() != roomRefKey) {
            sendMessageFirstTime();
        } else {

        }

    }
}
