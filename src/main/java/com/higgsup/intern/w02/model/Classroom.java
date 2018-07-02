package com.higgsup.intern.w02.model;

public class Classroom {
    private int id ;
    private String nameClass;
    private String describe;
    private int instructorId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public int getinstructorId() {
        return instructorId;
    }

    public void setinstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}
