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
import com.pam.chatlib.helpers.Tools;
import com.pam.chatlib.interfaces.AdapterClickListener;
import com.pam.chatlib.models.Connection;
import com.pam.chatlib.models.ItemConversationIds;
import com.pam.chatlib.models.ItemMessageIds;
import com.pam.chatlib.models.ItemUserIds;
import com.pam.chatlib.models.Message;
import com.pam.chatlib.models.MessageModel;
import com.pam.chatlib.models.User;
import com.vanniktech.emoji.EmojiEditText;

import java.io.Serializable;
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
    private RecyclerView connectionRecycler;
    private RecyclerView chatRecycler;

    User me;
    String room_id;
    ValueEventListener fitchMessages;

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

        databaseReference.child("user").child(currentUserToken).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                me = dataSnapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void setUserRecycler(RecyclerView userRecycler) {
        this.userRecycler = userRecycler;
    }
    public void setConnectionRecycler(RecyclerView connectionRecycler) {
        this.connectionRecycler = connectionRecycler;
    }
    public void setChatRecycler(RecyclerView chatRecycler) {
        this.chatRecycler = chatRecycler;
    }

    public void fitchMessages(String room_id) {
        this.room_id = room_id;
        if (chatRecycler == null || currentUserToken == null) {
            // TODO: 9/3/2022 create on successes interface
            return;
        }

        ChatAdapter1 adapter = new ChatAdapter1(chatRecycler.getContext(), currentUserToken, itemMessageIds);
        chatRecycler.setLayoutManager(new LinearLayoutManager(chatRecycler.getContext()));
        chatRecycler.setAdapter(adapter);

        if (fitchMessages!=null)
            databaseReference.child("chat").child(room_id).removeEventListener(fitchMessages);

        fitchMessages = databaseReference.child("chat").child(room_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> messages = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Message message = snapshot.getValue(Message.class);

                    messages.add(message);
                }
                adapter.addItems(messages);
                chatRecycler.scrollToPosition(messages.size() - 1);
                Toast.makeText(chatRecycler.getContext(), messages.size() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void fitchUsers(AdapterClickListener adapterClickListener) {
        if (userRecycler == null || currentUserToken == null) {
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

                    users.add(user);
                }
                adapter.addItems(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void fitchConnections(AdapterClickListener adapterClickListener) {
        if (connectionRecycler == null || currentUserToken == null) {
            // TODO: 9/3/2022 create on successes interface
            return;
        }

        ConnectionsAdapter adapter = new ConnectionsAdapter(connectionRecycler.getContext(), currentUserToken, itemConversationIds,adapterClickListener);
        connectionRecycler.setLayoutManager(new LinearLayoutManager(connectionRecycler.getContext()));
        connectionRecycler.setAdapter(adapter);


        databaseReference.child("contact").child(currentUserToken).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Connection> connections = new ArrayList<>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Connection connection = snapshot.getValue(Connection.class);

                    connections.add(connection);
                }
                adapter.addItems(connections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public Connection getConnectionFromUser(User user) {

            DatabaseReference reference = databaseReference.child("chat").push();
            String room_id = reference.getKey();
            fitchMessages(room_id);
            createRoom(user);
        return new Connection(user.getName(),user.getPic(),room_id);
    }
    public void sendMessage(String message) {


            DatabaseReference reference = databaseReference.child("chat").child(room_id).push();
           // String pushIdMessage = reference.getKey();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("id_user", currentUserToken);
            childUpdates.put("message", message);
            childUpdates.put("time", Tools.getTimeStringFormat());
            reference.setValue(childUpdates);




    }



    public void createRoom(User user) {
        // TODO: 9/6/2022  set room id

        DatabaseReference reference1 = databaseReference.child("contact").child(currentUserToken).push();

        DatabaseReference reference2 = databaseReference.child("contact").child(user.getToken()).push();

        Map<String, Object> room1 = new HashMap<>();
        room1.put("contact_name", user.getName());
        room1.put("pic", user.getPic()); // TODO: 9/6/2022 chang pic with user token to get dynamic pic and other mutable field
        room1.put("room_id", room_id);

        reference1.setValue(room1);

        Map<String, Object> room2 = new HashMap<>();
        room2.put("contact_name", me.getName());
        room2.put("pic", me.getPic());
        room2.put("room_id",room_id);

        reference2.setValue(room2);


    }

}
