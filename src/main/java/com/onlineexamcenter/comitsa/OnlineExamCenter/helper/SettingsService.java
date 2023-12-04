package com.onlineexamcenter.comitsa.OnlineExamCenter.helper;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Settings;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.SettingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SettingsService {
    @Autowired
    private SettingsRepo settingsRepo;
    public Optional<Settings> getSettingById(Long sid){
        return settingsRepo.findById(sid);
    }

    public List <Settings> getAllSettings() {
        return settingsRepo.findAll();
    }

    public Settings updateSettings(Settings settings){
        return settingsRepo.save(settings);
    }
}
