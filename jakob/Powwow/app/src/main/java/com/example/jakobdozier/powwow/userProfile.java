package com.example.jakobdozier.powwow;

public class userProfile {

    String username, firstName, lastName, email;
    int age;

    int inversions;

    public userProfile() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public int getInversions() { return inversions; }

    public void setInversions(int inversion) {
        this.inversions = inversion;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email){ this.email = email; }

    public String getEmail() { return email; }
}
