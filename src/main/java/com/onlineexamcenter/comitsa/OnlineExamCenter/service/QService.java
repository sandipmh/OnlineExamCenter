package com.onlineexamcenter.comitsa.OnlineExamCenter.service;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Question;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QService {
    @Autowired
    private QuestionRepo repo;

    public List<Question> saveQuestions(List<Question> questions){
        return repo.saveAll(questions);
    }
    public Question saveQuestion(Question question){
        return repo.save(question);
    }
}
