package com.example.GeminiTest;

import com.example.GeminiTest.DTO.CheckDTO;
import com.example.GeminiTest.DTO.PreparationPlanDTO;
import com.example.GeminiTest.DTO.PromptDTO;
import com.example.GeminiTest.DTO.TestUserDTO;
import com.fasterxml.jackson.core.Base64Variant;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/chat")
public class GeminiController {
    @Autowired
    private GeminiService geminiService;
    @PostMapping("/ask")
    public ResponseEntity<String> askGemini(@RequestBody PromptDTO promptDTO) {
        String response = geminiService.getAnswer(promptDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/checkAnswer")
    public ResponseEntity<String> checkAnswer(@RequestBody CheckDTO checkDTO) {
        String response = geminiService.checkUserAnswer(checkDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/testUser")
    public ResponseEntity<String> testUser(@RequestBody TestUserDTO testUserDTO) {
        String response = geminiService.testUserByQuestions(testUserDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/preparationPlan")
    public ResponseEntity<String> preparationPlan(@RequestBody PreparationPlanDTO preparationPlanDTO) {
        String response = geminiService.preparationPlan(preparationPlanDTO);
        return ResponseEntity.ok(response);
    }



}


