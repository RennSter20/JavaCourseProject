package hr.java.projekt.entitet;

public class PovijestBolesti {

    private Bolest[] bolesti;

    public PovijestBolesti(Bolest[] bolesti) {
        this.bolesti = bolesti;
    }

    public Bolest[] getBolesti() {
        return bolesti;
    }

    public void setBolesti(Bolest[] bolesti) {
        this.bolesti = bolesti;
    }
}
