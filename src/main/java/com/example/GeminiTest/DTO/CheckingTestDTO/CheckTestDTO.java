package com.example.GeminiTest.DTO.CheckingTestDTO;

import lombok.Data;

import java.util.List;

@Data
public class CheckTestDTO {
    private long sessionId;
    private List<QuestionCheckTestDTO> testList;
}
