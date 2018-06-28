package com.higgsup.intern.w02.model;

public class Instructor {
    private int id;
    private String name;
    private Number dateOfBirth;
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

    public Number getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Number dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
