package com.example.NetChatBackend.Controllers;

import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Requests.UpdateUserRequest;
import com.example.NetChatBackend.Response.ApiResponse;
import com.example.NetChatBackend.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	
	public UserController( UserService userService ) {
		this.userService = userService;
	}
	
	@GetMapping("/profile")
	public ResponseEntity< User > getUserProfileHandler( @RequestHeader("Authorization") String token) throws UserException {
		User user = userService.findUserProfile(token);
		
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{query}")
	public ResponseEntity< List<User> > searchUserHandler( @PathVariable("query") String query){
		List<User> users = userService.searchUser(query);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity< ApiResponse > updateUserHandler( @RequestBody UpdateUserRequest req,
	                                                        @RequestHeader("Authorization") String token) throws UserException {
		
		User user = userService.findUserProfile(token);
		userService.updateUser(user.getId(),req);
		
		ApiResponse res = new ApiResponse("USER UPDATED SUCCESFULLY", true);
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
}
