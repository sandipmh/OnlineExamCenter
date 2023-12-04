package com.onlineexamcenter.comitsa.OnlineExamCenter.controller;

import com.onlineexamcenter.comitsa.OnlineExamCenter.dto.ExamDTO;
import com.onlineexamcenter.comitsa.OnlineExamCenter.dto.ExamQuestionDTO;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Question;
import com.onlineexamcenter.comitsa.OnlineExamCenter.dto.QuestionsDTO;
import com.onlineexamcenter.comitsa.OnlineExamCenter.helper.*;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.*;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.UserRepo;
import com.onlineexamcenter.comitsa.OnlineExamCenter.service.AnswerSheetService;
import com.onlineexamcenter.comitsa.OnlineExamCenter.service.QService;
import com.onlineexamcenter.comitsa.OnlineExamCenter.service.SubjectService;
import com.onlineexamcenter.comitsa.OnlineExamCenter.utility.CsvUtility;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasicController {

    @Autowired
    private QService qService;
    @Autowired
    private AnswerSheetService answerSheetService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CountryService countryService;
    @Autowired
    private LanguageService languageService;

    @Autowired
    private SettingsService settingsService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ThemeService themeService;

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @Autowired
    private CsvUtility csvUtility;
    public List<Country> getCountries(){
        List<Country> countries = countryService.getCountries();
        return countries;
    }

    public List<Language> getLanguages(){
        List<Language> languages = languageService.getLanguages();
        return languages;
    }

    public User getUserByEmail(String email){
       return userRepo.findUserByEmail(email);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public Country getCountry(String countryName){
        return countryService.getCountry(countryName);
    }

    public User getCurrentUser(Principal principal){
        return getUserByEmail(principal.getName());
    }

    public Settings getSetting(Long sid){
        return settingsService.getSettingById(sid).get();
    }

    public Country getCountryByCode(Long countryCode){
        return countryService.getCountryByCode(countryCode);
    }

    public List <Settings> getSettings(){
        return settingsService.getAllSettings();
    }

    public Language getLanguage(Integer lid){
        return languageService.getLanguage(lid).get();
    }
    public Theme getThemeById(Long tid){
        return themeService.getThemeById(tid).get();
    }

    public Settings updateSettings(Settings settings){
        return settingsService.updateSettings(settings);
    }

    public List<Theme> getAllThemes(){
       return themeService.getAllThemes();
    }

    public Page<User> getUsers(int page){
        return userRepo.findAll(PageRequest.of(page,10));
    }

    public void removeUsers(Long uid){
        userRepo.deleteById(uid);
    }

    public List<User> searchUsers(String search){
        return userRepo.findBySearchTerm(search);
    }

    public Subjects SaveSubject(Subjects subject) {
        return subjectService.saveSubject(subject);
    }
    public Page<Subjects> getAllSubjects(int page){
        return subjectService.getAllSubjects(page);
    }

    public void removSubject(Long id){
        subjectService.removeSubjecctById(id);
    }

    public void removeSubjectsByIds(List<Long> ids){
        subjectService.removeSubjecctByIds(ids);
    }
    public Questions saveQuestion(Questions question){
        return questionService.saveQuestion(question);
    }

    public Page<Questions> getAllQuestions(int page){
        return questionService.getAllQuestions(page);
    }

    public List<Subjects> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    public void removQuestions(Long pid) {
        questionService.removeQuestions(pid);
    }

    public void removeQuestionsByIds(List<Long> checkedIds) {
        questionService.removeQuestions(checkedIds);
    }

    public List<Questions> getAllQuestionsBySubject() {
       return questionService.getAllQuestionsBySubject();
    }

    public List<Questions> getAllQuestionsBySubject(Long subjectIds) {
        return questionService.getAllQuestionsBySubject(subjectIds);
    }

    public Exam SaveExam(Exam exam) {
      return examService.saveExam(exam);
    }

    public Page<Exam> getAllExams(int page) {
        return examService.getAllExams(page);
    }

    public List<Questions> getAllQuestions(List<Long> questionIds) {
        return questionService.getAllQuestions(questionIds);
    }

    public void removeQuestionsBySubjectId(Long sid) {
        questionService.removeQuestionsBySubjectId(sid);
    }

    public void removeQuestionsBySubjectIds(List<Long> checkedIds) {
        questionService.removeQuestionsBySubjectIds(checkedIds);
    }

    public void removeExams(Long sid) {
        examService.removeExams(sid);
    }

    public void removeExams(List<Long> sids) {
        examService.removeExams(sids);
    }

    public <T> void exportDataToCSV(HttpServletResponse response,List<T> objects) {

        csvUtility.writeCsvData(response,objects);
    }

    public void exportToCsv(HttpServletResponse response,List<Subjects> subjects) throws IOException {
        csvUtility.exportToCsv(response,subjects);
    }

    public List<ExamDTO> getExams() {
        List<Exam> exams = examService.getLiveExams();
       return exams.stream().map(exam -> convertToDto(exam,ExamDTO.class)).collect(Collectors.toList());
    }
    public List<QuestionsDTO> getQuestionsByExamId(long eid) {
        List<Questions> questions = questionService.getAllQuestionsByExamId(eid);
        return questions.stream()
                .map(question -> convertToDto(question, QuestionsDTO.class))
                .collect(Collectors.toList());
    }

    public <T> T convertToDto(Object object, Class<T> dtoClass) {
        return modelMapper.map(object, dtoClass);
    }

    public ExamQuestionDTO getExam(Long eid){
        Exam exam = examService.getExamsByEid(eid);
        ExamDTO examDTO = convertToDto(exam, ExamDTO.class);
        List<QuestionsDTO> questions = exam.getQuestions().stream().map(question ->convertToDto(question, QuestionsDTO.class)).collect(Collectors.toList());
        return new ExamQuestionDTO(questions, examDTO);
    }

    public List<Question> getQuestionByExamId(long eid) {
        List<Questions> questions = questionService.getAllQuestionsByExamId(eid);
        return questions.stream()
                .map(question -> convertToDto(question, Question.class))
                .collect(Collectors.toList());
    }

    public AnswerSheet saveAnswerSheet(AnswerSheet sheet){
        return answerSheetService.saveAnswerSheet(sheet);
    }

    public List<Question> saveAllQuestion(List<Question> questionList){
        return qService.saveQuestions(questionList);
    }

    public Question saveAllQuestion(Question questionList){
        return qService.saveQuestion(questionList);
    }
    public Boolean alreadyEnrolled(Long examId,Long userId){
        return answerSheetService.IsEnrolled(examId,userId);
    }

    public AnswerSheet getExamByExamIdAndUserId(Long eid,Long uid){
        return answerSheetService.startExam(eid, uid);
    }
}
