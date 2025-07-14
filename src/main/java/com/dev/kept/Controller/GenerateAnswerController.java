package com.dev.kept.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.kept.Service.AiService;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class GenerateAnswerController {

    private final AiService AiService;

    public GenerateAnswerController(AiService AiService) {
        this.AiService = AiService;
    }

    @PostMapping("/generate-answer")
    public ResponseEntity<?> generateAnswer(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        if (question == null || question.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Question is required"));
        }

        String answer = AiService.generateAnswer(question);
        return ResponseEntity.ok(Map.of("answer", answer));
    }
}