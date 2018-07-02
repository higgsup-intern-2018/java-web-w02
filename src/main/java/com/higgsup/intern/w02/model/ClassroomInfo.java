package com.higgsup.intern.w02.model;

public class ClassroomInfo extends Classroom {
    private String instructorName;
    private int count;

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
