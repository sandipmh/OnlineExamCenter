package com.onlineexamcenter.comitsa.OnlineExamCenter.service;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.AnswerSheet;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.AnswerSheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerSheetService {
    @Autowired
    private AnswerSheetRepo repo;
    public AnswerSheet saveAnswerSheet(AnswerSheet answerSheet){
        return repo.save(answerSheet);
    }

    public Boolean IsEnrolled(Long examId, Long userId) {
       AnswerSheet sheet = repo.findByExamIdAndUserId(examId,userId);
       if (sheet == null){
           return false;
       }
        return true;
    }
    public AnswerSheet startExam(Long eid, Long uid){
        return repo.findByExamIdAndUserId(eid,uid);
    }
}
