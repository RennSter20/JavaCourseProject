package hr.java.projekt.entitet;

public class Hospital {

    private String imeBolnice;
    private String gradBolnice;
    private Integer brojZaposlenika;
    private Integer brojDoktora;
    private Integer ostaloOsoblje;
    private Integer brojTrenutnihPacijenata;
    private Integer brojSoba;
    private Integer budzet;

    public Hospital(String imeBolnice, String gradBolnice, Integer brojZaposlenika, Integer brojDoktora, Integer ostaloOsoblje, Integer brojTrenutnihPacijenata, Integer brojSoba, Integer budzet) {
        this.imeBolnice = imeBolnice;
        this.gradBolnice = gradBolnice;
        this.brojZaposlenika = brojZaposlenika;
        this.brojDoktora = brojDoktora;
        this.ostaloOsoblje = ostaloOsoblje;
        this.brojTrenutnihPacijenata = brojTrenutnihPacijenata;
        this.brojSoba = brojSoba;
        this.budzet = budzet;
    }

    public String getImeBolnice() {
        return imeBolnice;
    }

    public void setImeBolnice(String imeBolnice) {
        this.imeBolnice = imeBolnice;
    }

    public String getGradBolnice() {
        return gradBolnice;
    }

    public void setGradBolnice(String gradBolnice) {
        this.gradBolnice = gradBolnice;
    }

    public Integer getBrojZaposlenika() {
        return brojZaposlenika;
    }

    public void setBrojZaposlenika(Integer brojZaposlenika) {
        this.brojZaposlenika = brojZaposlenika;
    }

    public Integer getBrojDoktora() {
        return brojDoktora;
    }

    public void setBrojDoktora(Integer brojDoktora) {
        this.brojDoktora = brojDoktora;
    }

    public Integer getOstaloOsoblje() {
        return ostaloOsoblje;
    }

    public void setOstaloOsoblje(Integer ostaloOsoblje) {
        this.ostaloOsoblje = ostaloOsoblje;
    }

    public Integer getBrojTrenutnihPacijenata() {
        return brojTrenutnihPacijenata;
    }

    public void setBrojTrenutnihPacijenata(Integer brojTrenutnihPacijenata) {
        this.brojTrenutnihPacijenata = brojTrenutnihPacijenata;
    }

    public Integer getBrojSoba() {
        return brojSoba;
    }

    public void setBrojSoba(Integer brojSoba) {
        this.brojSoba = brojSoba;
    }

    public Integer getBudzet() {
        return budzet;
    }

    public void setBudzet(Integer budzet) {
        this.budzet = budzet;
    }
}
