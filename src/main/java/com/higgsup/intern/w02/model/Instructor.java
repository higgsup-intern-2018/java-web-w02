package com.higgsup.intern.w02.model;

public class Instructor {
    private int id;
    private String name_instructor;
    private int yearOfBirth;
    private String classroomId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_instructor() {
        return name_instructor;
    }

    public void setName_instructor(String name_instructor) {
        this.name_instructor = name_instructor;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }
}
