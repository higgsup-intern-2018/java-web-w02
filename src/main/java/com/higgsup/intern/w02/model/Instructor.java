package com.higgsup.intern.w02.model;

public class Instructor {
    private int id;
    private String nameInstructor;
    private int yearOfBirth;
    private String classroomId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameInstructor() {
        return nameInstructor;
    }

    public void setNameInstructor(String nameInstructor) {
        this.nameInstructor = nameInstructor;
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
