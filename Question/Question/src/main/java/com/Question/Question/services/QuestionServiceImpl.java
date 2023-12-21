package com.Question.Question.services;

import com.Question.Question.clients.AnswerClient;
import com.Question.Question.entities.Answer;
import com.Question.Question.entities.Question;
import com.Question.Question.repositories.QuestionRepository;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AnswerClient answerClient;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long questionID) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionID);
        return optionalQuestion.orElse(null);
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Long questionID, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionID);

        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setQuestion(updatedQuestion.getQuestion());
            // Update other fields as needed

            return questionRepository.save(existingQuestion);
        } else {
            return null; // Handle the case where the question with the given ID is not found
        }
    }

    @Override
    public void deleteQuestion(Long questionID) {
        questionRepository.deleteById(questionID);
    }

    //    @Override
//    public List<Answer> getAnswersForQuestion(Long questionID) {
//        // Assuming the Answer service is running on port 8080
//        String answerServiceUrl = "http://localhost:8080/answers/question/" + questionID;
//        // Use ParameterizedTypeReference to capture the generic type
//        ParameterizedTypeReference<List<Answer>> responseType = new ParameterizedTypeReference<List<Answer>>() {};
//
//        ResponseEntity<List<Answer>> responseEntity = restTemplate.exchange(answerServiceUrl, HttpMethod.GET, null, responseType);
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            return responseEntity.getBody();
//        } else {
//            // Handle other status codes if needed
//            return Collections.emptyList();
//        }
//    }
    @Override
    public List<Answer> getAnswersForQuestion(Long questionID) {
        return answerClient.getAnswersByQuestionID(questionID);
    }

    //

    @CircuitBreaker(name = "answerService", fallbackMethod = "demo")
    public Question getQuestionById2(Long questionID) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionID);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            // Fetch answers from Answer service
            List<Answer> answers = answerClient.getAnswersByQuestionID(questionID);
            question.setAnswers(answers);
            return question;
        } else {
            return null;
        }
    }

    //
    public String demo(CallNotPermittedException e) {
        String errorMessage = "Server not responding. Sorry Avadhoot.";
        return errorMessage;
    }
}
