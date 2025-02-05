package org.example.quizservice.Controller;

import org.example.quizservice.Model.QuestionWrapper;
import org.example.quizservice.Model.QuizDTO;
import org.example.quizservice.Model.Response;
import org.example.quizservice.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService service;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){

        return service.createQuiz(quizDTO.getCategoryName(), quizDTO.getNumQuestions(), quizDTO.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){

        return service.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses , @PathVariable int id){

        return service.calculateResult(id, responses);
    }

}
