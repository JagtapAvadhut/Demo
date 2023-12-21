package com.Question.Question.controllers;

import com.Question.Question.entities.Question;
import com.Question.Question.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/QuestionAndAnswer")
public class QuestionAnsAnswerController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/qid/{questionID}")
    public Question getQuestionById2(@PathVariable Long questionID) {
        return questionService.getQuestionById2(questionID);
    }

}
