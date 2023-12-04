package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Country {
    @Id
    @GeneratedValue
    private Long cid;
    private String countryName;
    private Long countryCode;

    public Country() {
    }

    public Country(Long cid, String countryName, Long countryCode) {
        this.cid = cid;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }
}
