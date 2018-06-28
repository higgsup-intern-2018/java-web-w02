package com.higgsup.intern.w02.model;

public class Student {
    private int id;
    private String name;
    private Number yearOfBirth;
    private String address;

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

    public Number getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Number yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
