package ru.gozhan.lab06.model;

public class User {

    private String surname;
    private String name;
    private String email;

    public User(String surname, String name, String email) {
        this.surname = surname;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

}
