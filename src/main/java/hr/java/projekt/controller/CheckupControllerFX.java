package hr.java.projekt.controller;

import hr.java.projekt.entitet.Checkup;
import hr.java.projekt.entitet.Patient;
import hr.java.projekt.models.CheckupModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

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
    private TableView<CheckupModel> tableView;
    @FXML
    private TableColumn<CheckupModel, String> nameColumn;
    @FXML
    private TableColumn<CheckupModel, String> surnameColumn;
    @FXML
    private TableColumn<CheckupModel, String> oibColumn;
    @FXML
    private TableColumn<CheckupModel, String> dateColumn;


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
    public void getAllCheckups(ActionEvent e){

        ObservableList<Checkup> recievedCheckups = FXCollections.observableArrayList();

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

            ObservableList<CheckupModel> checkupRecords = FXCollections.observableArrayList();

            for(int i = 0;i<recievedCheckups.size();i++){
                checkupRecords.add(new CheckupModel(recievedCheckups.get(i).getPatient().getName(), recievedCheckups.get(i).getPatient().getSurname(), recievedCheckups.get(i).getPatient().getOib(), recievedCheckups.get(i).getDate()));
            }

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            oibColumn.setCellValueFactory(new PropertyValueFactory<>("oib"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            tableView.setItems(checkupRecords);
        }else{

        }



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
