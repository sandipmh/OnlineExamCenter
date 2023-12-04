package com.onlineexamcenter.comitsa.OnlineExamCenter.dto;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Exam;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Options;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Subjects;

import java.util.List;

public class QuestionsDTO {
    private Long qid;
    private String question;
    private List<Options> options;
    private boolean singleSelection;
    private int marks;
    private Subjects subject;
    private List<Exam> exam;
    public QuestionsDTO() {
    }

    public QuestionsDTO(Long qid, String question, List<Options> options, boolean singleSelection, int marks, Subjects subject, List<Exam> exam) {
        this.qid = qid;
        this.question = question;
        this.options = options;
        this.singleSelection = singleSelection;
        this.marks = marks;
        this.subject = subject;
        this.exam = exam;
    }

    public List<Exam> getExam() {
        return exam;
    }

    public void setExam(List<Exam> exam) {
        this.exam = exam;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public boolean isSingleSelection() {
        return singleSelection;
    }

    public void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
