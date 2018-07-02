package com.higgsup.intern.w02.model;

import java.util.List;

public class StudentInfo extends Student {

    private List<Classroom> listClass;

    public List<Classroom> getListClass() {
        return listClass;
    }

    public void setListClass(List<Classroom> listClass) {
        this.listClass = listClass;
    }
}
