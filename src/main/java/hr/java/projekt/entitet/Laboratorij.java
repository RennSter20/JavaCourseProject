package hr.java.projekt.entitet;

public class Laboratorij {

    private Nalaz[] nalazi;

    public Nalaz[] getNalazi() {
        return nalazi;
    }

    public void setNalazi(Nalaz[] nalazi) {
        this.nalazi = nalazi;
    }

    public Laboratorij(Nalaz[] nalazi) {
        this.nalazi = nalazi;
    }
}
