package com.onlineexamcenter.comitsa.OnlineExamCenter.controller;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Question;
import com.onlineexamcenter.comitsa.OnlineExamCenter.dto.QuestionsDTO;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final BasicController controller;


    public MainController(BasicController controller) {
        this.controller = controller;

    }

    @RequestMapping("/")
    public String getHome(){
        return "redirect:/home";
    }
    @RequestMapping("/about")
    public String getAbout(){
        return "aboutus";
    }

    @RequestMapping("/exams/interface")
    public String startExam(@RequestParam ("eid") Long eid,Model model){
       List<QuestionsDTO> questions = controller.getQuestionsByExamId(eid);
        System.out.println(controller.getExam(eid).getExam().getExamTitle());
        System.out.println(controller.getExam(eid).getQuestions().get(0).getQuestion());
       questions.stream().forEach(question-> System.out.println(question.getQuestion()));
        model.addAttribute("questions",questions);
        model.addAttribute("exam",controller.getExam(eid));
        return "menu/examinterface";
    }
    @RequestMapping("/contact/us")
    public String getContactUs(){
        return "contactus";
    }
    @RequestMapping("/home")
    public String guestHome(){
        return "dashboard";
    }

    @RequestMapping("/signin")
    public String home(){
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("/all/results")
    public String allResults(){
        return "admin_menu/results";
    }

    @RequestMapping("/users")
    public String searchUsers(@RequestParam(defaultValue = "user") String search, Model model){
        List<User> users = controller.searchUsers(search);
        // System.out.println(users.get(0));
        model.addAttribute("users", users);
        return "admin_menu/manageusers";
    }

    @RequestMapping("/live/exams")
    public String getExams(Model model){
        controller.getExams().stream().forEach(examDTO -> System.out.println(examDTO.getExamTitle()+""+examDTO.getExamIcon()));
        model.addAttribute("exams", controller.getExams());
        return "liveexams";
    }

    @RequestMapping("/upcoming/exams")
    public String getUpComingExams(Model model){
        model.addAttribute("exams", controller.getExams());
        return "upcomingexams";
    }

    @RequestMapping("/upcoming/exams/user")
    public String registerExams(Model model, Principal principal){
        Long userId = controller.getCurrentUser(principal).getUid();
        model.addAttribute("userId",userId);
        model.addAttribute("exams", controller.getExams());
        return "menu/upcomingexams";
    }

    @RequestMapping("/live/exams/user")
    public String startExams(Model model){
        model.addAttribute("message","Registered Successfully");
        model.addAttribute("exams", controller.getExams());
        return "menu/liveexams";
    }

    @RequestMapping("/manage-users")
    public String manageUsers(@RequestParam(defaultValue = "0") int page, Model model){
        model.addAttribute("users", controller.getUsers(page));
        return "admin_menu/manageusers";
    }

    @RequestMapping("/manage-exams")
    public String manageExams(@RequestParam(defaultValue = "0") int page, Model model){
        model.addAttribute("subjects",controller.getAllSubjects());
        model.addAttribute("exams", controller.getAllExams(page));
        return "admin_menu/manageexams";
    }

    @RequestMapping("/manage-questions")
    public String manageQuestions(@RequestParam(defaultValue = "0") int page, Model model){
        model.addAttribute("questions", controller.getAllQuestions(page));
        model.addAttribute("subjects", controller.getAllSubjects());
        return "admin_menu/managequestions";
    }

    @RequestMapping("/manage-subjects")
    public String manageSubjects(@RequestParam(defaultValue = "0") int page, Model model){
        model.addAttribute("subjects", controller.getAllSubjects(page));
        return "admin_menu/managesubjects";
    }

    @PostMapping("save/subject")
    public String saveSubject(@ModelAttribute Subjects subject,Model model){
        controller.SaveSubject(subject);
        model.addAttribute("subjects", controller.getAllSubjects(0));
        return "redirect:/manage-subjects";
    }
    @PostMapping("/register/exams")
    @Transactional
    public String registerExams(@ModelAttribute AnswerSheet sheet, Model model, Principal principal){
            sheet.setUserId(controller.getCurrentUser(principal).getUid());
        if (! controller.alreadyEnrolled(sheet.getExamId(), sheet.getUserId()) && sheet.getUserId() != null) {
            sheet.setExamId(sheet.getExamId());
            sheet.setUserId(sheet.getUserId());
            AnswerSheet ansSheet = controller.saveAnswerSheet(sheet);
            List<Question> list = controller.getQuestionByExamId(sheet.getExamId());
            System.out.println("Question count "+ list.size());
            list.stream().map(question -> {
                question.setAnswerSheet(ansSheet);
//                question.setOptions(question.getOptions());
                return controller.saveAllQuestion(question);
            }).collect(Collectors.toList());
         List<Question> questionsList =   controller.saveAllQuestion(list);
            System.out.println("Question size after saving " + questionsList.size());
            model.addAttribute("msg","success");
        } else {
            model.addAttribute("msg","Already enrolled");
            System.out.println("Already enrolled");
        }
        model.addAttribute("exams",controller.getExams());
        return "menu/upcomingexams";
    }

    @PostMapping("save/exam")
    public String saveExam(@ModelAttribute Exam exam,Model model){
        List<Long> questionIds = exam.getQuestions().stream().map(questions -> questions.getQid()).collect(Collectors.toList());
        System.out.println("Question IDs : "+questionIds);
        List<Questions> questionsList = controller.getAllQuestions(questionIds);
        questionsList.stream().forEach(questions -> System.out.println(questions.getQuestion()));
        Subjects subject = questionsList.get(0).getSubject();
        exam.setQuestions(questionsList);
        exam.setTrendIcon("\\images\\new.png");
        exam.setExamIcon(subject.getIcon());
        exam.setSubject(subject.getTitle());
        exam.setPublic(false);
        controller.SaveExam(exam);
        model.addAttribute("exams", controller.getAllExams(0));
        return "redirect:/manage-exams";
    }

    @PostMapping("save/question")
    public String saveQuestion(@ModelAttribute Questions question, Model model){
        System.out.println();
        System.out.println("save question");

        List<Options> options = question.getOptions();
        options.stream().forEach(op-> System.out.println("option is "+op.getOptionText()));
        List<Options> optionsList = new ArrayList<>();
        optionsList =  options.stream().map(option -> {
                Options options1 = new Options();
                options1.setOptionText(option.getOptionText());
            return options1;
        }).collect(Collectors.toList());
        question.setOptions(optionsList);
        controller.saveQuestion(question);
        model.addAttribute("questions", controller.getAllQuestions(0));
        return "redirect:/manage-questions";
    }

    @PostMapping("/move-users")
    public String removeUsers(@RequestParam Long uid, Model model){
        controller.removeUsers(uid);
        return "redirect:/manage-users";
    }

    @Transactional
    @PostMapping("/move-subjects")
    public String removeSubjects(@RequestParam Long sid, Model model){

        controller.removeQuestionsBySubjectId(sid);
        controller.removSubject(sid);
        return "redirect:/manage-subjects";
    }

    @Transactional
    @PostMapping("/move-exams")
    public String removeExams(@RequestParam Long eid, Model model){
        controller.removeExams(eid);
        return "redirect:/manage-exams";
    }

    @Transactional
    @PostMapping("/move/exams")
    public String removeExams(@RequestParam List<Long> eids, Model model){
        controller.removeExams(eids);
        return "redirect:/manage-exams";
    }

    @Transactional
    @PostMapping("/move-questions")
    public String removeQuestions(@RequestParam Long qid, Model model){
        controller.removQuestions(qid);
        return "redirect:/manage-questions";
    }

    @Transactional
    @PostMapping("/move/questions")
    public String removeQuestionsByIds(@RequestParam List<Long> checkedIds, Model model){
        controller.removeQuestionsByIds(checkedIds);
        return "redirect:/manage-questions";
    }

    @Transactional
    @PostMapping("/move/subjects")
    public String removeSubjects(@RequestParam List<Long> checkedIds, Model model){
        controller.removeQuestionsBySubjectIds(checkedIds);
        controller.removeSubjectsByIds(checkedIds);
        return "redirect:/manage-subjects";
    }

    @RequestMapping("/welcome")
    public String welcome(Principal principal,Model model){
        model.addAttribute("bgcolor",controller.getCurrentUser(principal).getSettings().getTheme().getBgColor());
        System.out.println("user authority is "+ SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[admin]")) return "admin_menu/home";
        return "menu/home";
    }

    @RequestMapping("/settings")
    public String settings(Model model, Principal principal){
        User user = controller.getUserByEmail(principal.getName());
        Settings settings = controller.getSetting(user.getSettings().getSid());
        model.addAttribute("themes",controller.getAllThemes());
        model.addAttribute("languages",controller.getLanguages());
        model.addAttribute("setting",settings);
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[admin]")) return "admin_menu/settings";
        return "menu/settings";
    }

    @RequestMapping("/results")
    public String results(){
        return "menu/results";
    }

    @RequestMapping("/exams")
    public String exams(){
        return "menu/exams";
    }

    @RequestMapping("/profile")
    public String profile(Model model, Principal principal){
        model.addAttribute("countries",controller.getCountries());
        model.addAttribute("languages",controller.getLanguages());
        System.out.println(principal.getName());
        System.out.println(controller.getUserByEmail(principal.getName()));
        User user = controller.getUserByEmail(principal.getName());
        System.out.println(" Country code is "+user.getCountryCode());
        model.addAttribute("user",user);
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[admin]")) return "admin_menu/profile";
        return "menu/profile";
    }

    @PostMapping("/sign/user")
    public String signUp(@ModelAttribute User user, Model model){
        Country country = controller.getCountry("India");
        Language language = controller.getLanguage(1);
        Theme theme = controller.getThemeById(1L);
        Settings setting = new Settings();
        setting.setNotification(true);
        setting.setLanguage(language);
        setting.setTheme(theme);
        user.setCountryCode(country.getCountryCode());
        user.setCountry(country);
        user.setSettings(setting);
        user.setCountryCode(country.getCountryCode());
        try {
            User savedUser = controller.saveUser(user);
            model.addAttribute("scs_msg", "Congratulations! Your account has been created.");
            if (savedUser == null) model.addAttribute("err_msg", "Failed to save user data. Please try again later.");
        }catch (Exception e){
            model.addAttribute("err_msg", "Failed to save user data. Please try again later.");
            System.out.println(e);
        }
        return "/signup";
    }

    @PostMapping("/update/user")
    public String updateUser(@ModelAttribute User user, Model model,Principal principal){
        try {
            User currentUser = controller.getCurrentUser(principal);
            user.setUid(currentUser.getUid());
            user.setSettings(currentUser.getSettings());
            Country country = controller.getCountryByCode(user.getCountryCode());
            user.setCountry(country);
            User savedUser = controller.saveUser(user);
            model.addAttribute("scs_msg", "Congratulations! Your account has been created.");
            if (savedUser == null) model.addAttribute("err_msg", "Failed to save user data. Please try again later.");

        }catch (Exception e){
            model.addAttribute("err_msg", "Failed to save user data. Please try again later.");

        }
        return "redirect:/profile";
    }

    @PostMapping("/update/settings")
    public String updateSettings(@ModelAttribute Settings setting, Model model,Principal principal){
        try {
            User currentUser = controller.getCurrentUser(principal);
            Settings settings = controller.getSetting(currentUser.getSettings().getSid());
            Language language = controller.getLanguage(setting.getLanguage().getLid());
            Theme theme = controller.getThemeById(setting.getTheme().getTid());
            setting.setLanguage(language);
            setting.setTheme(theme);
            setting.setSid(settings.getSid());
            Settings savedSettings = controller.updateSettings(setting);
            System.out.println("Language "+ language.getLanguageName());
            System.out.println("Theme "+ theme.getName());
            model.addAttribute("scs_msg", "Congratulations! Your settings has been saved.");
            if (savedSettings == null) model.addAttribute("err_msg", "Failed to save user settings. Please try again later.");
        }catch (Exception e){
            model.addAttribute("err_msg", "Failed to save user data. Please try again later.");
            System.out.println("failed while updating settings "+ e);
        }
        return "redirect:/settings";
    }

@PostMapping("/logout")
public String logout(){
    return "redirect:/signin";
}
}
