package com.higgsup.intern.w02.model;

public class ClassroomInfo extends Classroom {
    private String instructorName;
    private int sumStudent;

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setSumStudent(int sumStudent) {
        this.sumStudent = sumStudent;
    }

    public int getSumStudent() {
        return sumStudent;
    }
}
