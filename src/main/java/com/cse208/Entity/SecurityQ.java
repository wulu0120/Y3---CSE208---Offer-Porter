package com.cse208.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SecurityQ {

    Integer qusId;
    String text;

    //Alt+insert 快捷键
    public SecurityQ(String text) {
        this.text=text;
    }
}
