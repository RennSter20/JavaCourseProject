import hr.java.projekt.controller.Controller;
import hr.java.projekt.database.Database;
import hr.java.projekt.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main extends Application {


    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        Application.launch(args);
        /*        Scanner unos = new Scanner(System.in);

        Database database = new Database();
        LoginController loginController = new LoginController(database);

        System.out.println("Dobrodošli u program HospitalH!");
        System.out.println("Ovdje možete upisivati, brisati ili izmjeniti preglede pacijenata!\n\n");

        loginController.login(unos);
        Controller controller = new Controller(unos, logger);
        controller.pocniProgram();*/
    }
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/loginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}