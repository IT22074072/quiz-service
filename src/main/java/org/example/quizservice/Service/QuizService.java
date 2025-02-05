package org.example.quizservice.Service;
import org.example.quizservice.Model.Quiz;
import org.example.quizservice.Model.Response;
import org.example.quizservice.feign.QuizInterface;
import org.example.quizservice.repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.example.quizservice.Model.QuestionWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepo;

//    @Autowired
//    QuestionRepository questionRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
          Quiz quiz= quizRepo.findById(id).get();
          List<Integer> questionIds = quiz.getQuestionIds();
          ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);


        return questions;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
//        List<Question> questionsFromDB = quiz.getQuestions();
          int right = 0;
//        int i = 0;
//        for(Response response: responses){
//            if(response.getResponse().equals(questionsFromDB.get(i).getRightAnswer())){
//                right++;
//            }
//            i++;
//        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
