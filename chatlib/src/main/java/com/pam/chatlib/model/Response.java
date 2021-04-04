package com.pam.chatlib.model;

import java.util.List;

public class Response{
	private List<UsersItem> users;
	private ChatRoom chatRoom;

	public List<UsersItem> getUsers(){
		return users;
	}

	public ChatRoom getChatRoom(){
		return chatRoom;
	}
}