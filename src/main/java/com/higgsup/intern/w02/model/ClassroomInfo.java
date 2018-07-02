package com.higgsup.intern.w02.model;

public class ClassroomInfo extends Classroom {
    private String name_instructor;
    private int count;

    public String getName_instructor() {
        return name_instructor;
    }

    public void setName_instructor(String name_instructor) {
        this.name_instructor = name_instructor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
