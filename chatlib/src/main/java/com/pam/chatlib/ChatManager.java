package com.pam.chatlib;


import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.pam.chatlib.adapter.ChatAdapter1;
import com.pam.chatlib.adapter.ConnectionsAdapter;
import com.pam.chatlib.adapter.UserAdapter;
import com.pam.chatlib.interfaces.AdapterClickListener;
import com.pam.chatlib.models.Connection;
import com.pam.chatlib.models.ItemConversationIds;
import com.pam.chatlib.models.ItemMessageIds;
import com.pam.chatlib.models.ItemUserIds;
import com.pam.chatlib.models.Message;
import com.pam.chatlib.models.MessageModel;
import com.pam.chatlib.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ChatManager {
    private String currentUserToken;
    DatabaseReference databaseReference;

    ItemUserIds itemUserIds;
    ItemConversationIds itemConversationIds;
    ItemMessageIds itemMessageIds;

    private RecyclerView userRecycler;
    private RecyclerView ConnectionRecycler;
    private RecyclerView chatRecycler;


    public ChatManager(String currentUserToken, DatabaseReference databaseReference, ItemUserIds itemUserIds, ItemConversationIds itemConversationIds, ItemMessageIds itemMessageIds) {
        this.currentUserToken = currentUserToken;
        this.databaseReference = databaseReference;
        this.itemUserIds = itemUserIds;
        this.itemConversationIds = itemConversationIds;
        this.itemMessageIds = itemMessageIds;
    }


    public static Builder Builder() {
        return new Builder();
    }

    public void setCurrentUserToken(String currentUserToken) {
        this.currentUserToken = currentUserToken;
    }

    public void setUserRecycler(RecyclerView userRecycler) {
        this.userRecycler = userRecycler;
    }
    public void setConnectionRecycler(RecyclerView connectionRecycler) {
        this.ConnectionRecycler = connectionRecycler;
    }
    public void setChatRecycler(RecyclerView chatRecycler) {
        this.chatRecycler = chatRecycler;
    }

    public void fitchMessages() {
        if (chatRecycler == null&&currentUserToken == null) {
            // TODO: 9/3/2022 create on successes interface
            return;
        }

        ChatAdapter1 adapter = new ChatAdapter1(chatRecycler.getContext(), currentUserToken, itemMessageIds);
        chatRecycler.setLayoutManager(new LinearLayoutManager(chatRecycler.getContext()));
        chatRecycler.setAdapter(adapter);


        databaseReference.child("chat/0").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Message> messages = new ArrayList<>();

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                           Message message = snapshot.getValue(Message.class);
                           Toast.makeText(chatRecycler.getContext(), snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                           messages.add(message);
                        }
                        adapter.addItems(messages);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(chatRecycler.getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
    public void fitchUsers(AdapterClickListener adapterClickListener) {
        if (userRecycler == null && currentUserToken == null) {
            // TODO: 9/3/2022 create on successes interface
            return;
        }

        UserAdapter adapter = new UserAdapter(userRecycler.getContext(), currentUserToken, itemConversationIds,adapterClickListener);
        userRecycler.setLayoutManager(new LinearLayoutManager(userRecycler.getContext()));
        userRecycler.setAdapter(adapter);


        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> users = new ArrayList<>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals(currentUserToken))
                        continue;
                    User user = snapshot.getValue(User.class);
                    Toast.makeText(userRecycler.getContext(), snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                    users.add(user);
                }
                adapter.addItems(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(userRecycler.getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void fitchConnections() {
        if (ConnectionRecycler == null && currentUserToken == null) {
            // TODO: 9/3/2022 create on successes interface
            return;
        }

        ConnectionsAdapter adapter = new ConnectionsAdapter(ConnectionRecycler.getContext(), currentUserToken, itemConversationIds);
        ConnectionRecycler.setLayoutManager(new LinearLayoutManager(ConnectionRecycler.getContext()));
        ConnectionRecycler.setAdapter(adapter);


        databaseReference.child("contact").child(currentUserToken).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Connection> connections = new ArrayList<>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Connection connection = snapshot.getValue(Connection.class);
                    Toast.makeText(userRecycler.getContext(), snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                    connections.add(connection);
                }
                adapter.addItems(connections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(userRecycler.getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void updateChanges(MessageModel messageModel) {
    }
    private void sendMessageFirstTime() {

        // create contact to receiver user and create new chat room
        DatabaseReference ref,ref2;

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);
        String time = simpleDateFormat.format(c);

        ref=databaseReference.child("chat").push();

        String pushIdRoom = ref.getKey();

        ref2=ref.child(pushIdRoom).push();

        String pushIdMessage = ref2.getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("id_user", "02");
        childUpdates.put("message", "AFin CV");
        childUpdates.put("message_id",pushIdMessage);
        childUpdates.put("time", time);

      ref2.setValue(childUpdates);
        //Log.e("Items", "REF 1 =" + roomRefKey + " and REF 2 =" + messageRefKey);
    }
    public void sendMessage() {

        if (false) {
            sendMessageFirstTime();
        }
        else {
            DatabaseReference reference = databaseReference.child("chat").child("0").push();

           // String pushIdMessage = reference.getKey();
         //   Log.d("TAG", "sendMessage: "+pushIdMessage);

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);
            String time = simpleDateFormat.format(c);

            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("id_user", "2");
            childUpdates.put("message", "ee");
            childUpdates.put("time", time);
            reference.setValue(childUpdates);
        }
    }
    public  void testMessage(){
        /*DatabaseReference databaseReference1;
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ssZZ", Locale.ENGLISH);
        String time = simpleDateFormat.format(c);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("id_user", "01");
        childUpdates.put("message", "Hmd onta");
        childUpdates.put("time", time);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference1=databaseReference.child("chat").child(pushIdRoom);*/
        Log.e("Item","REF = ");
    }



}
