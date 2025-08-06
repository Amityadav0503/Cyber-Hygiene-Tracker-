package com.example.cyberhygiene.model;

import java.util.Map;

public class AnswerRequest {
    private Map<Integer, String> answers;

    public AnswerRequest() {}

    public Map<Integer, String> getAnswers() { return answers; }
    public void setAnswers(Map<Integer, String> answers) { this.answers = answers; }
}
