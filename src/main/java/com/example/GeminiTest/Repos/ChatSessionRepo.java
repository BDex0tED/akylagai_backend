package com.example.GeminiTest.Repos;

import com.example.GeminiTest.Models.ChatSession;
import org.springframework.data.repository.CrudRepository;

public interface ChatSessionRepo extends CrudRepository<ChatSession, Long> {
}
