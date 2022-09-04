package com.pam.chatlib;

import com.google.firebase.database.DatabaseReference;
import com.pam.chatlib.models.ItemConversationIds;
import com.pam.chatlib.models.ItemMessageIds;
import com.pam.chatlib.models.ItemUserIds;

public class Builder{

    private DatabaseReference databaseReference;
    private String CurrentUserToken ;
    private ItemConversationIds itemConversationIds;
    private ItemMessageIds itemMessageIds;
    private ItemUserIds itemUserIds;

    public Builder setCurrentUserToken(String currentUserToken) {
        CurrentUserToken = currentUserToken;
        return this;
    }

    public Builder setDataReference(DatabaseReference dataReference) {
        this.databaseReference=dataReference;
        return this;
    }

    public Builder setItemUserIds(ItemUserIds itemUserIds) {
        this.itemUserIds = itemUserIds;

        return this;
    }

    public Builder setItemConversationIds(ItemConversationIds itemConversationIds) {
        this.itemConversationIds = itemConversationIds;
        return this;
    }
    public Builder setItemMessageIds(ItemMessageIds itemMessageIds) {
        this.itemMessageIds = itemMessageIds;

        return this;
    }

    public ChatManager build() {
        // TODO: 04/04/2021 add throw exption

        ChatManager chatManager = new ChatManager(CurrentUserToken, databaseReference,itemUserIds, itemConversationIds,itemMessageIds);
        //chatManager.init();
        return chatManager;
    }




}