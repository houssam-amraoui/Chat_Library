package com.pam.chatlib.models;

import java.io.Serializable;

public class Connection implements Serializable {
    String contact_name;
    String pic;
    String room_id;

  /*  public static final String CONNECTION_TYPE_MESSAGE = "MESSAGE";
    public static final String CONNECTION_TYPE_FAVORITE = "FAVORITE";
    public static final String CONNECTION_TYPE_VISITOR = "VISITOR";

    public static final String MESSAGE_TYPE_CHAT = "CHAT";
    public static final String MESSAGE_TYPE_MATCHED = "MATCHED";
    public static final String MESSAGE_TYPE_IMAGE = "IMAGE";
    public static final String MESSAGE_TYPE_CALL = "CALL";

    public static final String COL_CONNECTION_TYPE = "type";
    public static final String COL_MESSAGE_TYPE = "messageType";
    public static final String COL_USER_FROM = "fromUser";
    public static final String COL_USER_TO = "toUser";
    public static final String COL_USER_FROM_ID = "fromUserId";
    public static final String COL_USER_TO_ID = "toUserId";
    public static final String COL_IMAGE = "image";
    public static final String COL_TEXT = "text";
    public static final String COL_READ = "read";
    public static final String COL_READ_SENDER = "read_sender";
    public static final String COL_READ_RECEIVER = "read_receiver";
    public static final String COL_COUNT = "count";
    public static final String COL_MESSAGE = "message";
    public static final String COL_MESSAGE_ID = "messageId";
    public static final String COL_CREATED_AT = "createdAt";
    public static final String COL_UPDATED_AT = "updatedAt";

    public static final String COL_HIDDEN_SENDER = "hiddenBySender";
    public static final String COL_HIDDEN_RECEIVER = "hiddenByReceiver";*/



    public Connection() {
    }

    public Connection(String contact_name, String pic, String room_id) {
        this.contact_name = contact_name;
        this.pic = pic;
        this.room_id = room_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
