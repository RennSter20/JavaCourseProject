package hr.java.projekt.entitet;



public class Doctor extends User {


    private Integer soba;
    private Patient[] pacijenti;


    public Doctor(String JO, String ime, String prezime, String uloga) {
        super(JO, ime, prezime, uloga);
    }
    

    public Integer getSoba() {
        return soba;
    }

    public void setSoba(Integer soba) {
        this.soba = soba;
    }

    public Patient[] getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(Patient[] pacijenti) {
        this.pacijenti = pacijenti;
    }
}
