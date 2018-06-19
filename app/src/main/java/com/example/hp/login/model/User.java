package com.example.hp.login.model;

public class User {

    private String first_name, email_address, gender, sir_name, phone_number, birth_date;
    private  Integer id;

    public User(int anInt, String first_name, String email_address, String gender, String sir_name, String phone_number, String birth_date) {
        this.first_name = first_name;
        this.email_address = email_address;
        this.gender = gender;
        this.sir_name = sir_name;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getSir_name() {
        return sir_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getGender() {
        return gender;
    }


}
