package com.dev.kept.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AiServiceImpl implements AiService {

    @Value("${together.api.key}")
    private String togetherApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String generateAnswer(String question) {
        String url = "https://api.together.xyz/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(togetherApiKey);

        Map<String, Object> requestBody = Map.of(
            "model", "mistralai/Mixtral-8x7B-Instruct-v0.1",
            "messages", List.of(
                Map.of(
                    "role", "user",
                    "content", formatPrompt(question)
                )
            ),
            "temperature", 0.4,
            "max_tokens", 120
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            Map body = response.getBody();
            List choices = (List) body.get("choices");
            Map message = (Map) ((Map) choices.get(0)).get("message");
            return (String) message.get("content");
        } catch (Exception e) {
            return "❌ Error generating answer: " + e.getMessage();
        }
    }

    private String formatPrompt(String question) {
        return """
        You are an expert technical interviewer.
        Your task is to answer the following interview question in 2–4 concise, technically correct bullet points.
        Do NOT write a paragraph or theory explanation.
        Each bullet point must be direct and specific, not generic.
        If the question is about code or algorithms, provide steps or code logic in bullets.
        Question: "%s"
        
        Format:
        - Bullet point 1
        - Bullet point 2
        - ... (max 4)
        """.formatted(question);
    }
}
