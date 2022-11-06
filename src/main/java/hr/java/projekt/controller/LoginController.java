package hr.java.projekt.controller;

import hr.java.projekt.database.Database;

import java.util.Scanner;

public class LoginController{

    String ime;
    Database database;


    public LoginController(Database data){
        this.database = data;
    }



    public Integer provjeriOznaku(String unesenaOznaka, Database database){

        for(int i = 0; i<database.getZaposlenici().length; i++){
            if(database.getZaposlenici()[i][0].equals(unesenaOznaka)){
                return i;
            }
        }
        return -1;
    }
    public Integer login(Scanner unos){
        System.out.print("Unesite Vašu jedinstvenu oznaku: ");
        String JO = unos.nextLine();
        if(JO.equals("Odustajem")){
            System.exit(-1);
        }

        Integer indexOznaka;
        if((indexOznaka = provjeriOznaku(JO, database)) != -1){
            System.out.println("Dobrodošli " + database.getZaposlenici()[indexOznaka][1] + " "+ database.getZaposlenici()[indexOznaka][2] + "!");
        }else{
            System.out.println("Unesena kriva jedinstvena oznaka, molimo pokušajte ponovno!");
            System.out.println("Ako želite odustati, upišite \"Odustajem\".");
            login(unos);
        }



        return indexOznaka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
