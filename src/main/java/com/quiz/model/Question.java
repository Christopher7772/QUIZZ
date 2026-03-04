package com.quiz.model;

import java.io.Serializable;
import java.util.List;

// CORRECTION : implements Serializable obligatoire pour stocker dans la session HTTP
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;
    private List<String> options;
    private int correctAnswerIndex;
    private String category;

    public Question(String text, List<String> options, int correctAnswerIndex, String category) {
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.category = category;
    }

    public boolean isCorrect(int answerIndex) {
        return answerIndex == this.correctAnswerIndex;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    public void setCorrectAnswerIndex(int correctAnswerIndex) { this.correctAnswerIndex = correctAnswerIndex; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}