package com.onlineexamcenter.comitsa.OnlineExamCenter.helper;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Questions;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionsRepo repo;

    public Questions saveQuestion(Questions question){
        return repo.save(question);
    }
    public Page<Questions> getAllQuestions(int page){
        return repo.findAll(PageRequest.of(page,5));
    }

    public void removeQuestions(Long pid) {
        repo.deleteById(pid);
    }

    public void removeQuestions(List<Long> checkedIds) {
        repo.deleteAllById(checkedIds);
    }

    public List<Questions> getAllQuestionsBySubject() {
        return repo.findAll();
    }

    public List<Questions> getAllQuestionsBySubject(Long subjectIds) {
        return repo.findAllBySubjectSid(subjectIds);
    }

    public List<Questions> getAllQuestions(List<Long> questionIds) {
        return repo.findAllById(questionIds);
    }

    public void removeQuestionsBySubjectId(Long sid) {
        repo.deleteAllBySubjectSid(sid);
    }

    public void removeQuestionsBySubjectIds(List<Long> checkedIds) {
        checkedIds.stream().forEach(id-> repo.deleteAllBySubjectSid(id));
    }

    public List<Questions> getAllQuestionsByExamId(long eid) {
        return repo.findAllByExamEid(eid);
    }
}
