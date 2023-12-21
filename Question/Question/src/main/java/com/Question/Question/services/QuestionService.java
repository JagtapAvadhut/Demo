package com.Question.Question.services;

import com.Question.Question.entities.Answer;
import com.Question.Question.entities.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question getQuestionById(Long questionID);

    Question createQuestion(Question question);

    Question updateQuestion(Long questionID, Question updatedQuestion);

    void deleteQuestion(Long questionID);

    List<Answer> getAnswersForQuestion(Long questionID);
    Question getQuestionById2(Long questionID);

}
