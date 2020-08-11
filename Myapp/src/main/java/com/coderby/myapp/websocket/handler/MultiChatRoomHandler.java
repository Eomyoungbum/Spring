package com.coderby.myapp.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.coderby.myapp.websocket.model.ChatRepository;
import com.coderby.myapp.websocket.model.ChattingMessage;
import com.coderby.myapp.websocket.model.ChattingRoom;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MultiChatRoomHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(MultiChatRoomHandler.class);
	
	@Autowired
	private ChatRepository chatRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("메세지 전송 = {} : {}",session,message.getPayload());
		ChattingMessage msg = objectMapper.readValue(message.getPayload(), ChattingMessage.class);
		System.out.println(msg);
		ChattingRoom room = chatRepository.selectRoom(msg.getRoomId());
		System.out.println(room);
		room.handlerMessage(session, msg);
	}
	
}
