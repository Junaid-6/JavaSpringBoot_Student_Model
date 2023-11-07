package com.example.demo.Student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Table
@Entity
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private  Long id;
    private String name;
    @Transient
    private Integer age;
    private String email;
    private LocalDate dob;


    Student(){

    }
    public Student(String name,  String email, LocalDate dob){
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(Long id, String name,  String email, LocalDate dob){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {

        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {

        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "<h1>" +
                "Name: " + name + "<br>" +
                "Age: " + age + "<br>" +
                "Email: " + email + "<br>" +
                "DOB: " + dob + "<br>" +
                "ID: " + id + "<br>" +
                "</h1>";
    }
}
