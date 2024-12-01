package com.SDA.SpringExercise.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import java.time.LocalDate;


@Entity
@Table(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String employeeName;

    private long workedHours;

    @Column(name = "isPresent")
    private boolean isPresent;
    private LocalDate date;

    public Attendance() {

    }

    public Attendance(String employeeName, long workedHours, boolean isPresent, LocalDate date) {
        this.employeeName = employeeName;
        this.workedHours = workedHours;
        this.isPresent = isPresent;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(long workedHours) {
        this.workedHours = workedHours;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}



