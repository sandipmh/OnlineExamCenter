package com.onlineexamcenter.comitsa.OnlineExamCenter.helper;

import com.onlineexamcenter.comitsa.OnlineExamCenter.dto.ExamQuestionDTO;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Exam;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepo examRepo;

    public List<Exam> getAllExams(){
        return examRepo.findAll();
    }

    public List<Exam> getExamsByUserId(Long uid){
        return examRepo.findAllByUsers_Uid(uid);
    }

    public Exam saveExam(Exam exam) {
      return examRepo.save(exam);
    }

    public Page<Exam> getAllExams(int page) {
        return examRepo.findAll(PageRequest.of(page,10));
    }

    public void removeExams(Long sid) {
        examRepo.deleteById(sid);
    }
    public void removeExams(List<Long> sids) {
        sids.stream().forEach(id-> examRepo.deleteById(id));
    }

    public List<Exam> getLiveExams() {
        return examRepo.findAll();
    }

    public Exam getExamsByEid(Long eid) {
        return examRepo.findById(eid).get();
    }
}
