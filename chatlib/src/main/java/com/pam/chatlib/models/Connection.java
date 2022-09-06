package com.pam.chatlib.models;

import java.io.Serializable;

public class Connection implements Serializable {
    String contact_name;
    String pic;
    String room_id;

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
