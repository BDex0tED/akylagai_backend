package com.example.GeminiTest.Controllers;

import com.example.GeminiTest.DTO.*;
import com.example.GeminiTest.DTO.CheckingTestDTO.CheckTestDTO;
import com.example.GeminiTest.Services.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/chat")
public class GeminiController {
    @Autowired
    private GeminiService geminiService;
    @PostMapping("/ask")
    public ResponseEntity<String> askGemini(@RequestBody PromptDTO promptDTO, Principal principal) {
        String title = """
                $s, %s""".formatted(promptDTO.getResponseMode(), promptDTO.getPrompt());
        Long sessionId = geminiService.createSession(title);
        String response = geminiService.getAnswer(principal,sessionId, promptDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/checkAnswer")
    public ResponseEntity<String> checkAnswer(@RequestBody CheckDTO checkDTO, Principal principal) {
        String title = """
                $s, %s""".formatted(checkDTO.getUserQuestion(),checkDTO.getLanguage());
        Long sessionId = geminiService.createSession(title);


        String response = geminiService.checkUserAnswer(principal,sessionId, checkDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/testUser")
    public ResponseEntity<String> testUser(@RequestBody TestUserDTO testUserDTO, Principal principal) {
        String title = """
                $s""".formatted(testUserDTO.getQuestionsTopic());
        Long sessionId = geminiService.createSession(title);

        String response = geminiService.testUserByQuestions(principal, sessionId, testUserDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/preparationPlan")
    public ResponseEntity<String> preparationPlan(@RequestBody PreparationPlanDTO preparationPlanDTO, Principal principal) {
        String title = """
                $s""".formatted(preparationPlanDTO.getPrompt());
        Long sessionId = geminiService.createSession(title);

        String response = geminiService.preparationPlan(principal, sessionId, preparationPlanDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<MessageDTO>> getMessageHistory(@PathVariable Long sessionId, Principal principal) throws AccessDeniedException {
        List<MessageDTO> history = geminiService.getHistory(sessionId, principal);
        return ResponseEntity.ok(history);
    }

    @PostMapping("/checkTest")
    public ResponseEntity<String> checkTest(@RequestBody CheckTestDTO checkTestDTO, Principal principal) {
        String result = geminiService.checkTest(principal, checkTestDTO);
        return ResponseEntity.ok(result);
    }



}


