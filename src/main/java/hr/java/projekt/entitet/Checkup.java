package hr.java.projekt.entitet;

import java.time.LocalDate;

public class Checkup {

    private Patient patient;
    private LocalDate date;


    public Checkup(Patient patient, LocalDate datumPregleda) {
        this.patient = patient;
        this.date = datumPregleda;
    }

    public Checkup(String name, String surname, String OIB, LocalDate date){
        this.patient.setName(name);
        this.patient.setSurname(surname);
        this.patient.setOib(OIB);
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
