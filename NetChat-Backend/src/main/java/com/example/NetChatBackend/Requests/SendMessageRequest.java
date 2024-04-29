package com.example.NetChatBackend.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {
	
	private Integer userId;
	private Integer chatId;
	private String content;
}
