package com.onlineexamcenter.comitsa.OnlineExamCenter.repositories;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Country;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepo extends JpaRepository<Country,Long> {
    public Country findByCountryName(String countryName);
    public Country findByCountryCode(Long countryCode);

}
