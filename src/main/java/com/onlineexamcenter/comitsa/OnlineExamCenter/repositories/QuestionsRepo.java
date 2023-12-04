package com.onlineexamcenter.comitsa.OnlineExamCenter.repositories;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepo extends JpaRepository<Questions, Long> {
    List<Questions> findAllBySubjectSid(Long subjectIds);

    void deleteAllBySubjectSid(Long sid);
    List<Questions> findAllByExamEid(long eid);
//    List<QuestionsDTO> findAllQuestionsByExamId(Long eid);
}
