package hr.java.projekt.models;

import java.time.LocalDate;

public class CheckupModel {

    private String name;
    private String surname;
    private String oib;
    private LocalDate date;

    public CheckupModel(String name, String surname, String oib, LocalDate date) {
        this.name = name;
        this.surname = surname;
        this.oib = oib;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
