package hr.java.projekt.entitet;

import java.util.Date;

public class Bolest {

    private String nazivBolesti;
    private String detaljanOpis;
    private Date datumBolesti;

     Bolest(String nazivBolesti, String detaljanOpis, Date datumBolesti) {
        this.nazivBolesti = nazivBolesti;
        this.detaljanOpis = detaljanOpis;
        this.datumBolesti = datumBolesti;
    }

    public String getNazivBolesti() {
        return nazivBolesti;
    }

    public void setNazivBolesti(String nazivBolesti) {
        this.nazivBolesti = nazivBolesti;
    }

    public String getDetaljanOpis() {
        return detaljanOpis;
    }

    public void setDetaljanOpis(String detaljanOpis) {
        this.detaljanOpis = detaljanOpis;
    }

    public Date getDatumBolesti() {
        return datumBolesti;
    }

    public void setDatumBolesti(Date datumBolesti) {
        this.datumBolesti = datumBolesti;
    }
}
