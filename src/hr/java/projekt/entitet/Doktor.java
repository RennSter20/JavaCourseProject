package hr.java.projekt.entitet;



public class Doktor {

    private String ime;
    private String prezime;
    private String spec;
    private String titula;
    private Integer soba;
    private Pacijent[] pacijenti;
    private String JO;

    public Doktor(String JO, String ime) {
        this.JO = JO;
        this.ime = ime;
    }






    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public Integer getSoba() {
        return soba;
    }

    public void setSoba(Integer soba) {
        this.soba = soba;
    }

    public Pacijent[] getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(Pacijent[] pacijenti) {
        this.pacijenti = pacijenti;
    }
}
