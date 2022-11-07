package hr.java.projekt.controller;

import hr.java.projekt.entitet.Patient;
import hr.java.projekt.entitet.Checkup;
import hr.java.projekt.entitet.User;
import hr.java.projekt.iznimke.IznimkaNemaUpisanihPregleda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
public class LoginControllerFX {

    @FXML
    public TextField id;

    @FXML
    public Text errorText;

    public void login(ActionEvent e) throws IOException {

            try {
                if(checkIfUserExists(id.getText())){

                    Parent root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
                    Scene scene = new Scene(root);

                    Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                }else{

                    errorText.setText("Please try again!");
                    id.setText("");
                }
            } catch (SQLException ex) {
                System.out.println(ex);

            }
    }
    public Boolean checkIfUserExists(String uid) throws SQLException, IOException{
        Connection veza = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");

        Statement sqlStatement = veza.createStatement();
        ResultSet usersResultSet = sqlStatement.executeQuery(
                "SELECT * FROM USERS"
        );

        while(usersResultSet.next()){
            User newUser = getUserFromResultSet(usersResultSet);
            if(newUser.getUid().equals(uid)){
                return true;
            }
        }
        return false;
    }
    public static User getUserFromResultSet(ResultSet usersResultSet) throws SQLException{

        String uid = usersResultSet.getString("uid");
        String name = usersResultSet.getString("NAME");
        String surname = usersResultSet.getString("SURNAME");
        String role = usersResultSet.getString("ROLE");

        return new User(uid, name, surname, role);

    }



}
