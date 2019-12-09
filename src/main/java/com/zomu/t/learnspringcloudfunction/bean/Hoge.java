package com.zomu.t.learnspringcloudfunction.bean;

import java.io.Serializable;

/**
 * Sample Bean.
 */
public class Hoge implements Serializable {

    private String name;

    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
