package com.coderby.myapp.websocket.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ChattingRoom {

	public static ArrayList<Integer> roomIds = new ArrayList<>();
	private int roomId;
	private String roomName;
	private List<WebSocketSession> sessions
	= new ArrayList<>();
	
	public static ChattingRoom createRoom(String name) {
		ChattingRoom room = new ChattingRoom();
		ChattingRoom.roomIds.add(ChattingRoom.roomIds.size()+1);
		room.roomId = ChattingRoom.roomIds.size();
		//room.roomId = UUID.randomUUID().toString();
		room.roomName = name;
		return room;
	}
	
	public void handlerMessage(WebSocketSession session, ChattingMessage message) throws IOException{
		if(message.getMessageType().equals("Enter")) {
			sessions.add(session);
			message.setMessage(message.getUser()+"님이 입장하셨습니다.");
		}else if(message.getMessageType().equals("Leave")) {
			sessions.remove(session);
			message.setMessage(message.getUser()+"님이 퇴장하셨습니다.");
		}else {
			message.setMessage(message.getUser()+" : "+message.getMessage());
		}
		send(message);
	}
	
	private void send(ChattingMessage message) throws IOException{
		TextMessage txtMessage = new TextMessage(message.getMessage());
		for(WebSocketSession s : sessions) {
			s.sendMessage(txtMessage);
		}
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getSize() {
		return this.sessions.size();
	}
}







