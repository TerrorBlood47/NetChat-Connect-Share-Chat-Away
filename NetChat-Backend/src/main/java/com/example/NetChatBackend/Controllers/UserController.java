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

@CrossOrigin(origins = "http://localhost:3000")
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
	
	@GetMapping("/search")
	public ResponseEntity< List<User> > searchUserHandler( @RequestParam("name") String name){
		
		System.out.println("query : " + name);
		
		List<User> users = userService.searchUser(name);
		
		System.out.println("users : " + users);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity< ApiResponse > updateUserHandler( @RequestBody UpdateUserRequest req,
															@PathVariable Integer userId,
	                                                        @RequestHeader("Authorization") String token) throws UserException {
		
		User user = userService.findUserProfile(token);
		userService.updateUser(userId,req);
		
		ApiResponse res = new ApiResponse("USER UPDATED SUCCESSFULLY", true);
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
}
