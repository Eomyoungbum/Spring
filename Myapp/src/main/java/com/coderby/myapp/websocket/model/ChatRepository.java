package com.coderby.myapp.websocket.model;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.coderby.myapp.websocket.model.ChattingRoom;

@Repository
public class ChatRepository {

	private Map<Integer, ChattingRoom> roomMap
	= new HashMap<>();
	
	public List<ChattingRoom> loadAllRooms(){
		List<ChattingRoom> rooms = new ArrayList<>(roomMap.values());
		Collections.reverse(rooms);
		Iterator<ChattingRoom> rit = rooms.iterator();
		while(rit.hasNext()) {
			if(rit.next().getSize()==0) {
			rit.remove();
			}
		}
		return rooms;
	}
	
	public ChattingRoom selectRoom(int roomId) {
		return roomMap.get(roomId);
	}
	
	public ChattingRoom createChattingRoom(String name) {
		ChattingRoom room = ChattingRoom.createRoom(name);
		roomMap.put(room.getRoomId(), room);
		return room;
	}
	
}
