package com.Answer.Answer.services;

import com.Answer.Answer.entities.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> getAllAnswers();

    Answer getAnswerById(Long answerID);

    Answer createAnswer(Answer answer);

    Answer updateAnswer(Long answerID, Answer answer);

    void deleteAnswer(Long answerID);

    List<Answer> getAnswersByQuestionID(Long questionID);
}
