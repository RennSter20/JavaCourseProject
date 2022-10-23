package hr.java.projekt.entitet;

import java.time.LocalDate;
import java.util.Date;

public class Pregled {

    private Pacijent pacijent;
    private LocalDate datumPregleda;


    public Pregled(Pacijent pacijent, LocalDate datumPregleda) {
        this.pacijent = pacijent;
        this.datumPregleda = datumPregleda;

    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public LocalDate getDatumPregleda() {
        return datumPregleda;
    }

    public void setDatumPregleda(LocalDate datumPregleda) {
        this.datumPregleda = datumPregleda;
    }
}
