package com.example.NetChatBackend.Services;

import com.example.NetChatBackend.Exceptions.ChatException;
import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.Chat;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Requests.GroupChatRequest;

import java.util.List;

public interface ChatService {
	
	public Chat createChat( User reqUser, Integer userId2 ) throws UserException;
	public Chat findChatById(Integer chatId) throws ChatException;
	public Chat createGroup( GroupChatRequest req, User reqUser) throws UserException;
	
	public List<Chat> findAllChatByUserId(Integer userId) throws UserException;
	
	public Chat addUserToGroup(Integer userId, Integer chatId, User reqUser) throws UserException,ChatException;
	
	public Chat renameGroup(Integer chatId, String groupName, User reqUser)
			throws ChatException,UserException;
	
	public Chat removeFromGroup(Integer chatId, Integer userId, User reqUser)
			throws ChatException,UserException;
	
	public void deleteChat(Integer chatId, Integer userId) throws ChatException,UserException;
}
