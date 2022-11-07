package hr.java.projekt.entitet;

public class Patient {

    private String name;
    private String surname;
    private String oib;


    public Patient(String name, String surname, String oib) {
        this.name = name;
        this.surname = surname;
        this.oib = oib;

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

}
