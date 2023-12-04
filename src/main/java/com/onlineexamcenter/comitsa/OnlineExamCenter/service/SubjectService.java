package com.onlineexamcenter.comitsa.OnlineExamCenter.service;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Subjects;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepo subjectRepo;
    public Subjects saveSubject(Subjects subject){
        return subjectRepo.save(subject);
    }

    public Page<Subjects> getAllSubjects(int page){
        return subjectRepo.findAll(PageRequest.of(page,10));
    }

    public void removeSubjecctById(Long id) {
        subjectRepo.deleteById(id);
    }

    public void removeSubjecctByIds(List<Long> ids) {
        subjectRepo.deleteAllByIdInBatch(ids);
    }

    public List<Subjects> getAllSubjects() {
        return subjectRepo.findAll();
    }
}
