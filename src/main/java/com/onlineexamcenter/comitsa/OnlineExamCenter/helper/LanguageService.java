package com.onlineexamcenter.comitsa.OnlineExamCenter.helper;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Language;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepo languageRepo;

    public List<Language> getLanguages(){
        return languageRepo.findAll();
    }

    public Optional<Language> getLanguage(Integer lid) {
        return languageRepo.findById(lid);
    }
}
