package com.pam.chatlib.models;

public class Message  {
	 String id_user;
	 String message;
	 String time;

	/*public static final String SENDER_AUTHOR = "fromUser";
	public static final String SENDER_AUTHOR_ID = "fromUserId";

	public static final String RECEIVER_AUTHOR = "toUser";
	public static final String RECEIVER_AUTHOR_ID = "toUserId";

	public static final String MESSAGE = "message";
	public static final String MESSAGE_FILE = "messageFile";
	public static final String IS_MESSAGE_FILE = "isMessageFile";
	public static final String MESSAGE_FILE_UPLOADED = "fileUploaded";
	public String imagePath;

	public static final String COL_READ = "read";

	public static final String COL_CONNECTION = "Connections";
	public static final String COL_CONNECTION_ID = "ConnectionsId";*/


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
