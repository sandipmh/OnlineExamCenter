package com.onlineexamcenter.comitsa.OnlineExamCenter.helper;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Theme;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepo themeRepo;
    public Optional<Theme> getThemeById(Long tid){
        return themeRepo.findById(tid);
    }

    public List<Theme> getAllThemes(){
        return themeRepo.findAll();
    }

}
