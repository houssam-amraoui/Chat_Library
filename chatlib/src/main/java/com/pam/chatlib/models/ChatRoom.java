package com.pam.chatlib.models;

import java.util.List;

public class ChatRoom{
	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}