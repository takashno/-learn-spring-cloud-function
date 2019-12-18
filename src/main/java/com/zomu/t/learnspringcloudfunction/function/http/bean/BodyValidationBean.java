package com.zomu.t.learnspringcloudfunction.function.http.bean;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Body Bean.
 */
public class BodyValidationBean implements Serializable {

    @NotEmpty
    @Pattern(regexp = "[abc]+")
    private String name;

    @NotEmpty
    @Pattern(regexp = "[123]+")
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
