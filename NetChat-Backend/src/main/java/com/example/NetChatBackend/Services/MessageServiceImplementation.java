package com.example.NetChatBackend.Services;

import com.example.NetChatBackend.Exceptions.ChatException;
import com.example.NetChatBackend.Exceptions.MessageException;
import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.Chat;
import com.example.NetChatBackend.Models.Message;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Repositories.MessageRepository;
import com.example.NetChatBackend.Requests.SendMessageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImplementation implements MessageService{
	
	private MessageRepository messageRepository;
	private UserService userService;
	private ChatService chatService;
	
	public MessageServiceImplementation( MessageRepository messageRepository, UserService userService, ChatService chatService ) {
		this.messageRepository = messageRepository;
		this.userService = userService;
		this.chatService = chatService;
	}
	
	@Override
	public Message sendMessage( SendMessageRequest req ) throws UserException, ChatException {
		User user = userService.findUserById(req.getUserId());
		Chat chat = chatService.findChatById(req.getChatId());
		
		Message message = new Message();
		message.setChat(chat);
		message.setUser(user);
		message.setContent(req.getContent());
		message.setTimestamp(LocalDateTime.now());
		
		return messageRepository.save(message);
	}
	
	@Override
	public List< Message > getChatsMessages( Integer chatId, User reqUser ) throws ChatException, UserException {
		
		Chat chat = chatService.findChatById(chatId);
		
		if(!chat.getUsers().contains(reqUser)){
			throw new UserException("User not in chat : " + chat.getId());
		}
		
		List<Message> messages = messageRepository.findByChatId(chat.getId());
		
		return messages;
	}
	
	@Override
	public Message findMessageById( Integer messageId ) throws MessageException {
		
		Optional<Message> opt = messageRepository.findById(messageId);
		
		if ( opt.isPresent() ) {
			return opt.get();
		}
		
		throw new MessageException("Message not found with id : " + messageId);
	}
	
	@Override
	public void deleteMessage( Integer messageId, User reqUser ) throws MessageException {
		Message message = findMessageById(messageId);
		
		if ( message.getUser().getId().equals(reqUser.getId()) ){
			messageRepository.deleteById(messageId);
		} else {
			throw new MessageException("You can't delete another users message.");
		}
	}
}
