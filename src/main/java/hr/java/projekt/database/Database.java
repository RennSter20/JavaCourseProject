package hr.java.projekt.database;

import hr.java.projekt.entitet.Checkup;
import hr.java.projekt.entitet.Patient;
import hr.java.projekt.iznimke.IznimkaNemaUpisanihPregleda;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {

    String[][] zaposlenici = new String[][]{
            {"82371", "dr.", "Renato"},
            {"41812", "dr.", "Matej"},
            {"32181", "Recepcija", "Bruno"},
            {"43127", "Recepcija", "Luka"}};

    ArrayList<Checkup> pregledi = new ArrayList<>();

    public Database() {

    }

    public void insertNewPregled(Checkup pregled) throws SQLException, IOException{
            Connection veza = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");

            PreparedStatement stmnt = veza.prepareStatement("INSERT INTO PREGLEDI(IME, PREZIME, OIB, DATUM) VALUES(?,?,?,?)");
            stmnt.setString(1, pregled.getPatient().getName());
            stmnt.setString(2, pregled.getPatient().getSurname());
            stmnt.setString(3, pregled.getPatient().getOib());
            stmnt.setString(4, pregled.getDate().toString());
            stmnt.executeUpdate();
            veza.close();
    }

    public static List<Checkup> getAllPregledi() throws SQLException, IOException, IznimkaNemaUpisanihPregleda {

        List<Checkup> preglediZaIspis = new ArrayList<>();

        try {
            Connection veza = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");

            Statement sqlStatement = veza.createStatement();
            ResultSet patientsResultSet = sqlStatement.executeQuery(
                    "SELECT * FROM PREGLEDI"
            );

            Integer number = 0;

            while(patientsResultSet.next()){
                Checkup noviPregled = getPregledFromResultSet(patientsResultSet);
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
    public static Checkup getPregledFromResultSet(ResultSet pregledResultSet) throws SQLException{

        String ime = pregledResultSet.getString("IME");
        String prezime = pregledResultSet.getString("PREZIME");
        String OIB = pregledResultSet.getString("OIB");


        String tDatum = pregledResultSet.getString("DATUM");
        LocalDate tDate = LocalDate.parse(tDatum);


        return new Checkup(new Patient(ime,prezime,OIB), tDate);

    }
    public void addPregled(Checkup pregled) {
        this.pregledi.add(pregled);
    }

    public void setZaposlenici(String[][] zaposlenici) {
        this.zaposlenici = zaposlenici;
    }

    public ArrayList<Checkup> getPregledi() {
        return pregledi;
    }

    public Checkup getPregled(Integer index){
        return pregledi.get(index);
    }

    public void setPregledi(Checkup pregled) {
        this.pregledi.add(pregled);
    }



    public String[][] getZaposlenici() {
        return zaposlenici;
    }


}
