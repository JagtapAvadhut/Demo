package com.Question.Question.controllers;

import com.Question.Question.entities.Answer;
import com.Question.Question.entities.Question;
import com.Question.Question.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{questionID}")
    public Question getQuestionById(@PathVariable Long questionID) {
        return questionService.getQuestionById(questionID);
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/{questionID}")
    public Question updateQuestion(@PathVariable Long questionID, @RequestBody Question question) {
        return questionService.updateQuestion(questionID, question);
    }

    @DeleteMapping("/{questionID}")
    public void deleteQuestion(@PathVariable Long questionID) {
        questionService.deleteQuestion(questionID);
    }

    @GetMapping("/{questionID}/answers")
    public List<Answer> getAnswersForQuestion(@PathVariable Long questionID) {
        return questionService.getAnswersForQuestion(questionID);
    }


}
