package com.example.NetChatBackend.Controllers;

import com.example.NetChatBackend.Exceptions.ChatException;
import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.Chat;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Requests.GroupChatRequest;
import com.example.NetChatBackend.Requests.SingleChatRequest;
import com.example.NetChatBackend.Response.ApiResponse;
import com.example.NetChatBackend.Services.ChatService;
import com.example.NetChatBackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/chats")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/single/")
	public ResponseEntity< Chat > createChatHandler( @RequestBody SingleChatRequest singleChatRequest,
	                                                 @RequestHeader("Authorization") String jwt) throws UserException {
		
		User reqUser = userService.findUserProfile(jwt);
		Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserId());
		return ResponseEntity.ok().body(chat);
	}
	
	@PostMapping("/group/")
	public ResponseEntity< Chat > createGroupHandler( @RequestBody GroupChatRequest groupChatRequest,
	                                                  @RequestHeader("Authorization") String jwt) throws UserException {
		
		User reqUser = userService.findUserProfile(jwt);
		Chat chat = chatService.createGroup(groupChatRequest, reqUser);
		return ResponseEntity.ok().body(chat);
	}
	
	
	@GetMapping("/{chatId}")
	public ResponseEntity< Chat > findChatByHandler( @PathVariable Integer chatId
			,@RequestHeader("Authorzation") String jwt) throws UserException, ChatException {
		
		Chat chat = chatService.findChatById(chatId);
		return ResponseEntity.ok().body(chat);
	}
	
	
	@GetMapping("/user")
	public ResponseEntity< List<Chat> > createGroupHandler(@RequestHeader("Authorization") String jwt)
			throws UserException {
		
		User reqUser = userService.findUserProfile(jwt);
		List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
		return ResponseEntity.ok().body(chats);
	}
	
	
	@PutMapping("/{chatId}/add/{userId}")
	public ResponseEntity< Chat > addUserToGroupHandler(
			@PathVariable Integer chatId,
			@PathVariable Integer userId,
			@RequestHeader("Authorization") String jwt)
			throws UserException, ChatException {
		
		User reqUser = userService.findUserProfile(jwt);
		Chat chat = chatService.addUserToGroup(userId, chatId, reqUser);
		return ResponseEntity.ok().body(chat);
	}
	
	@PutMapping("/{chatId}/remove/{userId}")
	public ResponseEntity< Chat > removeUserFromGroupHandler(
			@PathVariable Integer chatId,
			@PathVariable Integer userId,
			@RequestHeader("Authorization") String jwt)
			throws UserException, ChatException {
		
		User reqUser = userService.findUserProfile(jwt);
		Chat chat = chatService.removeFromGroup(userId, chatId, reqUser);
		return ResponseEntity.ok().body(chat);
	}
	
	@DeleteMapping("/delete/{chatId}")
	public ResponseEntity< ApiResponse > deleteChatHandler(
			@PathVariable Integer chatId,
			@RequestHeader("Authorization") String jwt)
			throws UserException, ChatException {
		
		User reqUser = userService.findUserProfile(jwt);
		chatService.deleteChat(chatId, reqUser.getId());
		
		ApiResponse res = new ApiResponse("chat is deleted successfully",true);
		
		return ResponseEntity.ok().body(res);
	}
	
	
}
