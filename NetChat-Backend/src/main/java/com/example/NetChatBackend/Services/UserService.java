package com.example.NetChatBackend.Services;

import com.example.NetChatBackend.Exceptions.UserException;
import com.example.NetChatBackend.Models.User;
import com.example.NetChatBackend.Requests.UpdateUserRequest;

import java.util.List;

public interface UserService {
	
	public User findUserById(Integer id) throws UserException;
	public User findUserProfile(String jwt) throws UserException;
	public User updateUser( Integer userId, UpdateUserRequest req) throws UserException;
	public List<User> searchUser(String query);
}
