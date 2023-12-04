package com.onlineexamcenter.comitsa.OnlineExamCenter.controller;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JsonController {

    @Autowired
    private BasicController controller;
    @RequestMapping("/get/questions")
    public List<Questions> getQuestionsBySubject(@RequestParam Long subjectIds){
        return controller.getAllQuestionsBySubject(subjectIds);
    }
    public List<Subjects> getSubject(){

        List<Subjects> subjects = controller.getAllSubjects();
        return controller.getAllSubjects();
    }

    public List<Questions> getQuestions(){

        List<Subjects> subjects = controller.getAllSubjects();
        return controller.getAllQuestions(0).stream().collect(Collectors.toList());
    }

    public List<Exam> getExams(){
        return controller.getAllExams(0).stream().collect(Collectors.toList());
    }

    @GetMapping("/export/subjects")
    public void exportSubjects(HttpServletResponse response) throws IOException {
        controller.exportDataToCSV(response,getSubject());
    }

    @GetMapping("/export/questions")
    public void exportQuestions(HttpServletResponse response) throws IOException {
        controller.exportDataToCSV(response,getQuestions());
    }

    @GetMapping("/export/exams")
    public void exportExams(HttpServletResponse response) throws IOException {
        controller.exportDataToCSV(response,getExams());
    }

    @GetMapping("/csv")
    public void exportDataToCsv(HttpServletResponse response) throws IOException {
        controller.exportToCsv(response,getSubject());
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(@RequestParam("eid") Long eid){
        return controller.getQuestionByExamId(eid);
    }

    @GetMapping("/exams")
    public AnswerSheet getExam(@RequestParam("eid") Long eid,@RequestParam("uid") Long uid){
        return controller.getExamByExamIdAndUserId(eid, uid);
    }

}
