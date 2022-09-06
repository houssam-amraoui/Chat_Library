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


        databaseReference.child("chat").child(room_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Message> messages = new ArrayList<>();

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                           Message message = snapshot.getValue(Message.class);
                           Toast.makeText(chatRecycler.getContext(), snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                           messages.add(message);
                        }
                        adapter.addItems(messages);
                        chatRecycler.scrollToPosition(messages.size()-1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(chatRecycler.getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(connectionRecycler.getContext(), snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                    connections.add(connection);
                }
                adapter.addItems(connections);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(connectionRecycler.getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void sendMessageFirstTime() {

        // create contact to receiver user and create new chat room
        DatabaseReference ref,ref2;

        ref=databaseReference.child("chat").push();

        String pushIdRoom = ref.getKey();

        ref2=ref.child(pushIdRoom).push();

        String pushIdMessage = ref2.getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("id_user", "02");
        childUpdates.put("message", "AFin CV");
        childUpdates.put("message_id",pushIdMessage);
        childUpdates.put("time", Tools.getTimeStringFormat());

      ref2.setValue(childUpdates);
        //Log.e("Items", "REF 1 =" + roomRefKey + " and REF 2 =" + messageRefKey);
    }
    public void sendMessage(String message, Serializable item) {


        if (item instanceof User || room_id == null) {
            DatabaseReference reference = databaseReference.child("chat").push();
            room_id = reference.getKey();

            User user = (User) item;
            createRoom(user);
            fitchMessages(room_id);
        }

            DatabaseReference reference = databaseReference.child("chat").child(room_id).push();
           // String pushIdMessage = reference.getKey();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("id_user", currentUserToken);
            childUpdates.put("message", message);
            childUpdates.put("time", Tools.getTimeStringFormat());
            reference.setValue(childUpdates);




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
