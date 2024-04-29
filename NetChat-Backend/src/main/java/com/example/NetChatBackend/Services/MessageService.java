package com.example.NetChatBackend.Services;

import com.example.NetChatBackend.Exceptions.ChatException;
import com.example.NetChatBackend.Exceptions.MessageException;
import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.Message;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Requests.SendMessageRequest;

import java.util.List;

public interface MessageService {
	
	public Message sendMessage( SendMessageRequest req) throws UserException, ChatException;
	
	public List<Message> getChatsMessages(Integer chatId, User reqUser) throws ChatException, UserException;
	
	public Message findMessageById(Integer messageId) throws MessageException;
	
	public void deleteMessage(Integer messageId, User reqUser) throws MessageException;
}
