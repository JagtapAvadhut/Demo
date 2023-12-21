package com.Answer.Answer.services;

import com.Answer.Answer.entities.Answer;
import com.Answer.Answer.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getAnswerById(Long answerID) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerID);
        return optionalAnswer.orElse(null);
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer updateAnswer(Long answerID, Answer updatedAnswer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerID);

        if (optionalAnswer.isPresent()) {
            Answer existingAnswer = optionalAnswer.get();
            existingAnswer.setAnswer(updatedAnswer.getAnswer());
            existingAnswer.setQuestionID(updatedAnswer.getQuestionID());

            return answerRepository.save(existingAnswer);
        } else {
            return null; // Handle the case where the answer with the given ID is not found
        }
    }

    @Override
    public void deleteAnswer(Long answerID) {
        answerRepository.deleteById(answerID);
    }

    @Override
    public List<Answer> getAnswersByQuestionID(Long questionID) {
        return answerRepository.findByQuestionID(questionID);
    }
}
