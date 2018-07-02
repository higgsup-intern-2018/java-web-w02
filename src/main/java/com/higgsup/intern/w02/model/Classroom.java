package com.higgsup.intern.w02.model;

public class Classroom
{
    private int id;
    private String name;
    private String description;
    private int instructorId;
    private String instructorName;
    private int studentEnroll;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorName(){return this.instructorName; }

    public void setInstructorName(String instructorName)
    {
        this.instructorName = instructorName;
    }

    public int getStudentEnroll(){return this.studentEnroll;}

    public void setStudentEnroll(int studentCount){this.studentEnroll = studentCount;}

    public String toString()
    {
        return name;
    }
}
