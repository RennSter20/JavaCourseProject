package hr.java.projekt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControllerFX {

    @FXML
    public TextField id;

    public void login(ActionEvent e) throws IOException {
        if(id.getText().equals("82371")){
            System.out.println("Login successful");
        }

        Parent root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

}
