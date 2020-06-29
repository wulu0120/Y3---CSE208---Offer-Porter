package com.cse208.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class User {

    private int id;
    private Integer qusId;
    private String name, pwd, re_pwd, ans, quest;
    private Date registerDate;

    public User(){

    }
    // Constructor
    public User(String name, String pwd, String re_pwd, Integer qusId, String ans,String quest){
        this.name = name;
        this.pwd = pwd;
        this.re_pwd = re_pwd;
        this.registerDate = new Date();
        this.qusId = qusId;
        this.ans = ans;
        this.quest = quest;

    }

    // Getter && Setter
    public int getId() {return id;}

    public void setId(int id) { this.id = id; }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPwd(){
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRe_pwd(){
        return re_pwd;
    }

    public void setRe_pwd(String re_pwd) {
        this.re_pwd = re_pwd;
    }

    public Integer getQusId() {
        return qusId;
    }

    public void setQusId(Integer qusId) {
        this.qusId = qusId;
    }

    public String getAns(){
        return ans;
    }

    public void setAns(String ans){
        this.ans = ans;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }
}
