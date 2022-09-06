package com.pam.chatlib.models;

public class Message  {
	 String id_user;
	 String message;
	 String time;


	public Message() {
	}
	public Message(String id_user, String message, String time) {
		this.id_user = id_user;
		this.message = message;
		this.time = time;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id_user='" + id_user + '\'' +
				", message='" + message + '\'' +
				", time='" + time + '\'' +
				'}';
	}
}
