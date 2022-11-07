package hr.java.projekt.controller;

import hr.java.projekt.entitet.Checkup;
import hr.java.projekt.entitet.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class CheckupControllerFX implements Initializable {

    @FXML
    private TextField newName;
    @FXML
    private TextField newSurname;
    @FXML
    private TextField newOIB;
    @FXML
    private DatePicker newDate;
    @FXML
    private Text dateErrorMessage;
    @FXML
    private Text errorMessage;
    @FXML
    private Text successMessage;

    @FXML
    private ListView listOfCheckups;


    public void addNewCheckup(ActionEvent e){
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");
            PreparedStatement stmnt = connection.prepareStatement("INSERT INTO CHECKUPS(ID, NAME, SURNAME, OIB, DATE) VALUES(?,?,?,?,?)");

            stmnt.setInt(1, Math.abs(new Random().nextInt()));
            stmnt.setString(2, newName.getText());
            stmnt.setString(3, newSurname.getText());
            stmnt.setString(4, newOIB.getText());

            if(checkIfFormIsValid(newDate.getValue())){
                stmnt.setString(5, newDate.getValue().toString());
            }else{
                return;
            }

            stmnt.executeUpdate();
            connection.close();

            successMessage.setText("Checkup added successfully!");
            emptyFields();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public Boolean checkIfFormIsValid(LocalDate date){
        Boolean dates = false;
        Boolean fields = false;

        if(checkIfDateIsValid(date)){
            dates = true;
        }
        if(!checkIfTextFieldsEmpty()){
            fields = true;
        }

        return dates && fields;
    }
    public Boolean checkIfDateIsValid(LocalDate date){
        if(date == null){
            dateErrorMessage.setText("Please choose valid date!");
            successMessage.setText("");
            return false;
        }else
        if(date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())){
            dateErrorMessage.setText("Please choose valid date!");
            successMessage.setText("");
            return false;
        }else{
            dateErrorMessage.setText("");
            return true;
        }
    }
    public Boolean checkIfTextFieldsEmpty(){
        if(newName.getText().equals("") || newSurname.getText().equals("") || newOIB.getText().equals("")){
            errorMessage.setText("Please fill out all text fields!");
            successMessage.setText("");
            return true;
        }else{
            errorMessage.setText("");
            return false;
        }
    }
    public void emptyFields(){
        newName.setText("");
        newSurname.setText("");
        newOIB.setText("");
        newDate.setValue(null);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public List<Checkup> getAllCheckups(ActionEvent e){

        List<Checkup> recievedCheckups = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");
            PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM CHECKUPS");

            ResultSet set = stmnt.executeQuery();

            while(set.next()){
                recievedCheckups.add(getCheckupFromResultSet(set));
            }
            connection.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(recievedCheckups.size() > 0){
            listOfCheckups.getItems().addAll(recievedCheckups);
        }
            return null;


    }
    public static Checkup getCheckupFromResultSet(ResultSet pregledResultSet) throws SQLException{

        String name = pregledResultSet.getString("NAME");
        String surname = pregledResultSet.getString("SURNAME");
        String OIB = pregledResultSet.getString("OIB");


        String tDate = pregledResultSet.getString("DATE");
        LocalDate finalDate = LocalDate.parse(tDate);


        return new Checkup(new Patient(name,surname,OIB), finalDate);

    }


}
