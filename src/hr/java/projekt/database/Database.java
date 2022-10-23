package hr.java.projekt.database;

import hr.java.projekt.entitet.Pacijent;
import hr.java.projekt.entitet.Pregled;

import java.util.ArrayList;

public class Database {

    String[][] zaposlenici = new String[][]{
            {"82371", "dr.", "Renato"},
            {"41812", "dr.", "Matej"},
            {"32181", "Recepcija", "Bruno"},
            {"43127", "Recepcija", "Luka"}};

    ArrayList<Pregled> pregledi = new ArrayList<>();

    public Database() {

    }

    public void addPregled(Pregled pregled) {
        this.pregledi.add(pregled);
    }

    public void setZaposlenici(String[][] zaposlenici) {
        this.zaposlenici = zaposlenici;
    }

    public ArrayList<Pregled> getPregledi() {
        return pregledi;
    }

    public Pregled getPregled(Integer index){
        return pregledi.get(index);
    }

    public void setPregledi(Pregled pregled) {
        this.pregledi.add(pregled);
    }



    public String[][] getZaposlenici() {
        return zaposlenici;
    }


}
