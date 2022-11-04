package hr.java.projekt.entitet;

public class Nalaz {

    private String imePacijenta;
    private String prezimePacijenta;

    private String opisNalaza;

    public Nalaz(String imePacijenta, String prezimePacijenta, String opisNalaza) {
        this.imePacijenta = imePacijenta;
        this.prezimePacijenta = prezimePacijenta;
        this.opisNalaza = opisNalaza;
    }

    public String getImePacijenta() {
        return imePacijenta;
    }

    public void setImePacijenta(String imePacijenta) {
        this.imePacijenta = imePacijenta;
    }

    public String getPrezimePacijenta() {
        return prezimePacijenta;
    }

    public void setPrezimePacijenta(String prezimePacijenta) {
        this.prezimePacijenta = prezimePacijenta;
    }

    public String getOpisNalaza() {
        return opisNalaza;
    }

    public void setOpisNalaza(String opisNalaza) {
        this.opisNalaza = opisNalaza;
    }
}
