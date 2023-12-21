package com.Question.Question.clients;

import com.Question.Question.entities.Answer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "ANSWER-SERVICE")
public interface AnswerClient {

    @GetMapping("/answers/question/{questionID}")
    @CircuitBreaker(name = "answerServiceBreaker", fallbackMethod = "fallbackMethod")
   @Retry(name = "answerServiceRetry")
    List<Answer> getAnswersByQuestionID(@PathVariable Long questionID);

    default List<Answer> fallbackMethod(Long questionID, Throwable throwable) {
        // Handle fallback logic, return a default response or throw a custom exception
        Answer defaultAnswer = new Answer();
        defaultAnswer.setAnswer("This IS Default answer");
        defaultAnswer.setAnswerID(0L);
        defaultAnswer.setQuestionID(0);

        return Collections.singletonList(defaultAnswer);
    }
}
