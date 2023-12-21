package com.Answer.Answer.controllers;

import com.Answer.Answer.entities.Answer;
import com.Answer.Answer.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{answerID}")
    public Answer getAnswerById(@PathVariable Long answerID) {
        return answerService.getAnswerById(answerID);
    }

    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }

    @PutMapping("/{answerID}")
    public Answer updateAnswer(@PathVariable Long answerID, @RequestBody Answer answer) {
        return answerService.updateAnswer(answerID, answer);
    }

    @DeleteMapping("/{answerID}")
    public void deleteAnswer(@PathVariable Long answerID) {
        answerService.deleteAnswer(answerID);
    }

    @GetMapping("/question/{questionID}")
    public List<Answer> getAnswersByQuestionID(@PathVariable Long questionID) throws InterruptedException {
        Thread.sleep(1000);
        return answerService.getAnswersByQuestionID(questionID);
    }
}
