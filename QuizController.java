package com.example.cyberhygiene.controller;

import com.example.cyberhygiene.model.AnswerRequest;
import com.example.cyberhygiene.model.FeedbackResponse;
import com.example.cyberhygiene.model.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuizController {

    private final List<Question> questions = List.of(
        new Question(1, "Do you use strong, unique passwords?"),
        new Question(2, "Do you enable two-factor authentication (2FA)?"),
        new Question(3, "Do you regularly update your software?"),
        new Question(4, "Do you avoid clicking suspicious links?"),
        new Question(5, "Do you back up your important data regularly?"),
        new Question(6, "Do you use antivirus software?"),
        new Question(7, "Do you avoid using public Wi-Fi for sensitive activities?"),
        new Question(8, "Do you log out from accounts on shared devices?"),
        new Question(9, "Do you check website URLs before entering information?"),
        new Question(10, "Do you limit sharing personal information online?")
    );

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return questions;
    }

    @PostMapping("/submit")
    public FeedbackResponse submitAnswers(@RequestBody AnswerRequest answerRequest) {
        Map<Integer, String> answers = answerRequest.getAnswers();
        int score = 0;
        for (Question q : questions) {
            String ans = answers.get(q.getId());
            if ("yes".equalsIgnoreCase(ans)) {
                score++;
            }
        }
        String feedback;
        if (score >= 8) {
            feedback = "Excellent! Your cyber hygiene practices are very good.";
        } else if (score >= 5) {
            feedback = "Good! But there's room for improvement.";
        } else {
            feedback = "Warning! You need to improve your cyber hygiene.";
        }
        return new FeedbackResponse(score, questions.size(), feedback);
    }
}
