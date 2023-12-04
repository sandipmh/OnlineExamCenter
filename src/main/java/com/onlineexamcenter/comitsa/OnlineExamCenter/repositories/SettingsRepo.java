package com.onlineexamcenter.comitsa.OnlineExamCenter.repositories;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SettingsRepo extends JpaRepository<Settings,Long> {
}
