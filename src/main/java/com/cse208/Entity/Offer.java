package com.cse208.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class Offer {

    private String username, graduateSchool, currentMajor, experience;
    private Double gpa, IELTS;   // Language Grade
    private Integer uId, offerId, TOEFL, gmat;          // GRE || GMAT 成绩
    private String gre, masterSchool, admittedMajor;    // 缺少offer picture

    public Integer getOfferId() {
        return offerId;
    }

    public Integer getUId() { return uId; }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getCurrentMajor() {
        return currentMajor;
    }

    public void setCurrentMajor(String currentMajor) {
        this.currentMajor = currentMajor;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Integer getTOEFL() {
        return TOEFL;
    }

    public void setTOEFL(Integer TOEFL) {
        this.TOEFL = TOEFL;
    }

    public Double getIELTS() {
        return IELTS;
    }

    public void setIELTS(Double IELTS) {
        this.IELTS = IELTS;
    }

    public String getGre() {
        return gre;
    }

    public void setGre(String gre) {
        this.gre = gre;
    }

    public Integer getGmat() {
        return gmat;
    }

    public void setGmat(Integer gmat) {
        this.gmat = gmat;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    public String getMasterSchool() {
        return masterSchool;
    }

    public void setMasterSchool(String masterSchool) {
        this.masterSchool = masterSchool;
    }

    public String getAdmittedMajor() {
        return admittedMajor;
    }

    public void setAdmittedMajor(String admittedMajor) {
        this.admittedMajor = admittedMajor;
    }

}
