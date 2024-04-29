package com.example.NetChatBackend.Requests;


import lombok.Data;

@Data
public class UpdateUserRequest {
	
	private String fullname;
	private String profile_pic;
}
