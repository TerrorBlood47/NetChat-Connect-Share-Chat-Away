package com.example.NetChatBackend.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	
	private String jwt;
	private Boolean isAuth;
	
	
}
