package com.example.flashstudy.model;

public class Flashcard {
    private static final int MAX_TEXT_LENGTH = 255;

    private String question;
    private String answer;
    private int correctCount;
    private int attemptCount;

    // Default constructor for JSON libraries
    public Flashcard() {
    }

    public Flashcard(String question, String answer) {
        if (question == null || answer == null)
            throw new IllegalArgumentException("question and answer required");
        if (question.length() > MAX_TEXT_LENGTH || answer.length() > MAX_TEXT_LENGTH)
            throw new IllegalArgumentException("question and answer are too long");
        this.question = question;
        this.answer = answer;
        this.correctCount = 0;
        this.attemptCount = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void recordAttempt(boolean correct) {
        attemptCount++;
        if (correct)
            correctCount++;
    }

    public double getAccuracy() {
        return attemptCount == 0 ? 0.0 : (double) correctCount / attemptCount;
    }

    @Override
    public String toString() {
        return "Q: " + question + " | A: " + answer;
    }
}
