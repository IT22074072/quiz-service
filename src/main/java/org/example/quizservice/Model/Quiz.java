package org.example.quizservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    //if you have numbers or specific type - ElementCollection
    //if you have different entity or table = ManyToMany...
    @ElementCollection // or CascadeType.PERSIST depending on your case
    private List<Integer> questionIds;
    public Quiz() {}

    public Quiz(int id, String title, List<Integer> questionIds) {
        this.id = id;
        this.title = title;
        this.questionIds = questionIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questionIds +
                '}';
    }


}
