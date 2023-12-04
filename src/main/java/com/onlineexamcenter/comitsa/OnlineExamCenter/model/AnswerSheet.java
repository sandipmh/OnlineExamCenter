package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AnswerSheet {
    @Id
    @GeneratedValue
    private Long aid;
    private Long examId;
    private Long userId;
    @OneToMany(mappedBy = "answerSheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questionList;


    public AnswerSheet() {
    }

    public AnswerSheet(Long examId, Long userId) {
        this.examId = examId;
        this.userId = userId;
    }

    public AnswerSheet(Long aid, Long examId, Long userId, List<Question> questionList) {
        this.aid = aid;
        this.examId = examId;
        this.userId = userId;
        this.questionList = questionList;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
