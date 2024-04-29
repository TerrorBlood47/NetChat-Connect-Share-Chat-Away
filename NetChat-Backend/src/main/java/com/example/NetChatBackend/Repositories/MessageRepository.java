package com.example.NetChatBackend.Repositories;

import com.example.NetChatBackend.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository< Message, Integer> {
	@Query("SELECT m FROM Message m join m.chat c WHERE c.id = :chatId")
	public List<Message> findByChatId(@Param("chatId") Integer chatId);
}
