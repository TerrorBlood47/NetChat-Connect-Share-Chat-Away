package com.example.NetChatBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class ChatException extends Exception{
	
	public ChatException(String message) {
		super(message);
	}
	
	
	
}
