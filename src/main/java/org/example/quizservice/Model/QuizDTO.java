package org.example.quizservice.Model;

import lombok.Data;

@Data
public class QuizDTO {
    private String categoryName;
    private int numQuestions;
    private String title;
}
