package com.onlineexamcenter.comitsa.OnlineExamCenter.repositories;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.AnswerSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerSheetRepo extends JpaRepository<AnswerSheet,Long> {
//    AnswerSheet findByExamId_and_UserId(Long examId, Long userId);
AnswerSheet findByExamIdAndUserId(Long examId, Long userId);
}
