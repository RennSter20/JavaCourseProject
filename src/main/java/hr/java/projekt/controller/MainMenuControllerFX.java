package hr.java.projekt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuControllerFX {

    @FXML
    private void loadCheckUpMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/checkupMenu.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) (((Node)e.getSource()).getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}
