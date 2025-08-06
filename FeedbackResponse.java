package com.example.cyberhygiene.model;

public class FeedbackResponse {
    private int score;
    private int total;
    private String feedback;

    public FeedbackResponse() {}

    public FeedbackResponse(int score, int total, String feedback) {
        this.score = score;
        this.total = total;
        this.feedback = feedback;
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
