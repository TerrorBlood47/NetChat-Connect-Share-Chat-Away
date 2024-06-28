package com.example.NetChatBackend.Controllers;

import com.example.NetChatBackend.Exceptions.ChatException;
import com.example.NetChatBackend.Exceptions.MessageException;
import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.Message;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Requests.SendMessageRequest;
import com.example.NetChatBackend.Response.ApiResponse;
import com.example.NetChatBackend.Services.MessageService;
import com.example.NetChatBackend.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	private MessageService messageService;
	private UserService userService;
	
	
	public MessageController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public ResponseEntity< Message > sendMessageHandler( @RequestBody SendMessageRequest req,
	                                                     @RequestHeader("Authorization") String jwt ) throws ChatException, UserException {
		User user = userService.findUserProfile(jwt);
		Message message = messageService.sendMessage(req);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	@GetMapping("/chat/{chatId}")
	public ResponseEntity< List<Message> > getChatsMessagesHandler(@PathVariable Integer chatId,
	                                                        @RequestHeader("Authorization") String jwt ) throws ChatException, UserException {
		
		User user = userService.findUserProfile(jwt);
		List<Message> messages = messageService.getChatsMessages(chatId, user);
		
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{messageId}")
	public ResponseEntity< ApiResponse > deleteMessageHandler( @PathVariable Integer messageId,
	                                                           @RequestHeader("Authorization") String jwt ) throws UserException, MessageException {
		
		User user = userService.findUserProfile(jwt);
		messageService.deleteMessage(messageId, user);
		
		ApiResponse res = new ApiResponse("Message deleted successfully", true);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
