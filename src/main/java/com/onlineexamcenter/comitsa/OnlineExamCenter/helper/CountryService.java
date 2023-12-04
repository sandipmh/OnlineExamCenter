package com.onlineexamcenter.comitsa.OnlineExamCenter.helper;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Country;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    public List<Country> getCountries(){
        return countryRepo.findAll();
    }

    public Country getCountry(String countryName) {
        return countryRepo.findByCountryName(countryName);
    }
    public Country getCountryByCode(Long countryCode){
        return countryRepo.findByCountryCode(countryCode);
    }
}
