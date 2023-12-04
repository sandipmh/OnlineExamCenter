package com.onlineexamcenter.comitsa.OnlineExamCenter.dto;

import java.util.List;

public class ExamQuestionDTO {

    private List<QuestionsDTO> questions;
    private ExamDTO exam;

    public ExamQuestionDTO() {
    }

    public ExamQuestionDTO(List<QuestionsDTO> questions, ExamDTO exam) {
        this.questions = questions;
        this.exam = exam;
    }

    public List<QuestionsDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsDTO> questions) {
        this.questions = questions;
    }

    public ExamDTO getExam() {
        return exam;
    }

    public void setExam(ExamDTO exam) {
        this.exam = exam;
    }
}
