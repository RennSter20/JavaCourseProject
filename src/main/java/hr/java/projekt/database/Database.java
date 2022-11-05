package hr.java.projekt.database;

import hr.java.projekt.entitet.Pacijent;
import hr.java.projekt.entitet.Pregled;
import hr.java.projekt.iznimke.IznimkaNemaUpisanihPregleda;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Database {

    String[][] zaposlenici = new String[][]{
            {"82371", "dr.", "Renato"},
            {"41812", "dr.", "Matej"},
            {"32181", "Recepcija", "Bruno"},
            {"43127", "Recepcija", "Luka"}};

    ArrayList<Pregled> pregledi = new ArrayList<>();

    public Database() {

    }

    public void insertNewPregled(Pregled pregled) throws SQLException, IOException{
            Connection veza = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");

            PreparedStatement stmnt = veza.prepareStatement("INSERT INTO PREGLEDI(IME, PREZIME, OIB, DATUM) VALUES(?,?,?,?)");
            stmnt.setString(1, pregled.getPacijent().getIme());
            stmnt.setString(2, pregled.getPacijent().getPrezime());
            stmnt.setString(3, pregled.getPacijent().getOib());
            stmnt.setString(4, pregled.getDatumPregleda().toString());
            stmnt.executeUpdate();
            veza.close();
    }

    public static List<Pregled> getAllPregledi() throws SQLException, IOException, IznimkaNemaUpisanihPregleda {

        List<Pregled> preglediZaIspis = new ArrayList<>();

        try {
            Connection veza = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");

            Statement sqlStatement = veza.createStatement();
            ResultSet patientsResultSet = sqlStatement.executeQuery(
                    "SELECT * FROM PREGLEDI"
            );

            Integer number = 0;

            while(patientsResultSet.next()){
                Pregled noviPregled = getPregledFromResultSet(patientsResultSet);
                preglediZaIspis.add(noviPregled);
                number++;
            }

            if(number == 0){
                throw new IznimkaNemaUpisanihPregleda("Broj pregleda je jednak 0!");
            }

            veza.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return preglediZaIspis;
    }

    public static Pregled getPregledFromResultSet(ResultSet pregledResultSet) throws SQLException{

        String ime = pregledResultSet.getString("IME");
        String prezime = pregledResultSet.getString("PREZIME");
        String OIB = pregledResultSet.getString("OIB");


        String tDatum = pregledResultSet.getString("DATUM");
        LocalDate tDate = LocalDate.parse(tDatum);


        return new Pregled(new Pacijent(ime,prezime,OIB), tDate);

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
