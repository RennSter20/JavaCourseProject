package hr.java.projekt.entitet;

import java.time.LocalDate;

public class Checkup {

    private Patient pacijent;
    private LocalDate datumPregleda;


    public Checkup(Patient pacijent, LocalDate datumPregleda) {
        this.pacijent = pacijent;
        this.datumPregleda = datumPregleda;
    }

    public Checkup(String ime, String prezime, String OIB, LocalDate datum){
        this.pacijent.setName(ime);
        this.pacijent.setSurname(prezime);
        this.pacijent.setOib(OIB);
        this.datumPregleda = datum;
    }

    public Patient getPacijent() {
        return pacijent;
    }

    public void setPacijent(Patient pacijent) {
        this.pacijent = pacijent;
    }

    public LocalDate getDatumPregleda() {
        return datumPregleda;
    }

    public void setDatumPregleda(LocalDate datumPregleda) {
        this.datumPregleda = datumPregleda;
    }
}
