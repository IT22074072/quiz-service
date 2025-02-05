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
//        Optional<Quiz> quiz= quizRepo.findById(id);
//        List<Question> questionsFromDB = quiz.get().getQuestions();
       List<QuestionWrapper> questionsForUser = new ArrayList<>();
//        for(Question q: questionsFromDB) {
//
//            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOpt1(), q.getOpt2(), q.getOpt3(), q.getOpt4());
//            questionsForUser.add(qw);
//        }
//

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
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
