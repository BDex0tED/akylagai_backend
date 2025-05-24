package com.example.GeminiTest;

import com.example.GeminiTest.DTO.CheckDTO;
import com.example.GeminiTest.DTO.PreparationPlanDTO;
import com.example.GeminiTest.DTO.PromptDTO;
import com.example.GeminiTest.DTO.TestUserDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
    private final ChatClient.Builder chatClientBuilder;

    @Autowired
    public GeminiService(ChatClient.Builder chatClientBuilder) {
        this.chatClientBuilder = chatClientBuilder;
    }

    public String getAnswer(PromptDTO promptDTO) {
        ChatClient client = chatClientBuilder.build();
        String promptString = """
    Определи школьный предмет вопроса, затем ответь на него.
    Поддерживаемые предметы:кыргыз тил, кыргыз адабияты, математика, физика, химия, литература, история, кыргыз тарыхы, биология, география, обществознание.
    Язык ответа: %s
    Вопрос: %s
    Стиль ответа: %s
    Ответь следуя следующему режиму: %s
""".formatted(promptDTO.getLanguage(), promptDTO.getPrompt(), promptDTO.getTypeOfResponse(), promptDTO.getResponseMode());
        String response = client.prompt(promptString).call().content();
        return response;
    }
    public String checkUserAnswer(CheckDTO checkDTO) {
        ChatClient client = chatClientBuilder.build();
        String promptString = """
        Определи школьный предмет вопроса, затем проверь мой ответ.
        Вопрос:%s
        Ответ:%s
        Язык ответа: %s
""".formatted(checkDTO.getUserAnswer(), checkDTO.getUserQuestion(), checkDTO.getLanguage());
        String response = client.prompt(promptString).call().content();
        return response;
    }
    public String testUserByQuestions(TestUserDTO testUserDTO) {
        ChatClient client = chatClientBuilder.build();
        String promptString = """
                Определи школьный предмет вопроса: %s, затем составь %s вопросов по этой теме
                Язык ответа: %s
                Сложность вопросов: %s
                """.formatted(testUserDTO.getQuestionsTopic(), testUserDTO.getQuestionsNum(),
                testUserDTO.getLanguage(), testUserDTO.getDifficulty());
        String response = client.prompt(promptString).call().content();
        return response;
    }
    public String preparationPlan(PreparationPlanDTO preparationPlanDTO) {
        ChatClient client = chatClientBuilder.build();
        String promptString = """
                Определи школьный предмет вопроса: %s, а затем составь план подготовки
                чтобы прям хорошо знать эту тему.
                Дополнительные пожелания:%s
               """.formatted(preparationPlanDTO.getTopic(), preparationPlanDTO.getPrompt());
        String response = client.prompt(promptString).call().content();
        return response;
    }
}
