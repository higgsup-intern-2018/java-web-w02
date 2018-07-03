package com.higgsup.intern.w02.model;

import java.util.List;

public class StudentInfo extends Student {
    private List<Classroom> classroomList;

    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    public void setClassroomList(List<Classroom> classroomList) {
        this.classroomList = classroomList;
    }
}
