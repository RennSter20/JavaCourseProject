package hr.java.projekt.controller;

import hr.java.projekt.database.Database;
import hr.java.projekt.entitet.Pacijent;
import hr.java.projekt.entitet.Pregled;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import hr.java.projekt.iznimke.IznimkaNemaUpisanihPregleda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {
    
    private Integer sljedeciKorak;
    private Integer indexKorisnika;
    private Scanner unos;
    private Database database;

    private static Logger logger;

    public Controller(Scanner unos, Integer indexKorisnika, Database database, Logger logger) {
        this.unos = unos;
        this.indexKorisnika = indexKorisnika;
        this.database = database;
        this.logger = logger;
    }

    public void pocniProgram(){
            redirectController(odabirOpcije(unos));
    }

    public static Integer odabirOpcije(Scanner unos){

        Boolean nastaviPetlju = false;
        Integer temp = null;
        do{

            try{
                System.out.println("Odaberite jednu od ponuđenih opcija: ");

                System.out.println("1. Pregledi");

                temp = unos.nextInt();
                unos.nextLine();

                if(temp != 1){
                    System.out.println("Neispravan unos!");
                    nastaviPetlju = true;
                }else{
                    nastaviPetlju = false;
                }

            }catch (InputMismatchException e){
                System.out.println("Neispravan unos!");
                nastaviPetlju = true;
                logger.info(e.getMessage(), e);
            }

        }while(nastaviPetlju);

        return temp;
    }

    private void redirectController(Integer broj){
        switch (broj){
            case 1:
                preglediMenu();
                break;
            //TO DO više menija
        }
    }

    private void preglediMenu(){


        Integer temp = null;
        Boolean nastaviPetlju = false;

        do{
            try{
                System.out.println("Odaberite jednu od ponuđenih opcija: ");
                System.out.println("1. Unos pregleda");
                System.out.println("2. Izmjena pregleda");
                System.out.println("3. Brisanje pregleda");
                System.out.println("4. Ispis svih pregleda");
                System.out.print(">>  ");

                temp = unos.nextInt();
                if(temp < 1 || temp > 4){
                    System.out.println("Neispravan unos!");
                    nastaviPetlju = true;
                    unos.nextLine();
                }else{
                    nastaviPetlju = false;
                }

            }catch (InputMismatchException e){
                System.out.println("Neispravan unos!");
                logger.info(String.valueOf(e.getStackTrace()));
            }
        }while(nastaviPetlju);
        unos.nextLine();

        switch(temp){
            case 1:
                unosPregleda();
                break;
            case 2:
                izmjenaPregleda();
                break;
            case 3:
                try {
                    brisanjePregleda();
                } catch (IznimkaNemaUpisanihPregleda e) {
                    System.out.println(e.getMessage());
                    logger.info(e.getMessage(), e);
                    preglediMenu();
                }
                break;
            case 4:
                ispisPregleda();
                break;
            default:
                System.out.println("Nije unesen ispravan broj, pokušajte ponovno!");
                preglediMenu();
        }
    }

    private void unosPregleda(){
        System.out.println("\n\nUnos pregleda: ");

        System.out.print("Ime: ");
        String tempIme = unos.nextLine();

        System.out.print("Prezime: ");
        String tempPrezime = unos.nextLine();

        System.out.print("OIB: ");
        String tempOIB = unos.nextLine();

        System.out.println("Datum formata dd.mm.yyyy.");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate tempDatum = LocalDate.parse(unos.nextLine(), dateFormat);

        database.setPregledi(new Pregled(new Pacijent(tempIme, tempPrezime, tempOIB), tempDatum));


        try {
            Connection veza = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/production", "student", "student");

            PreparedStatement stmnt = veza.prepareStatement("INSERT INTO PREGLEDI(IME, PREZIME) VALUES(?,?)");
            stmnt.setString(1, tempIme);
            stmnt.setString(2, tempPrezime);

            stmnt.executeUpdate();
            veza.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Pregled unesen!");
        System.out.println("Vracanje na pocetni menu...\n\n");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.info(e.getMessage(), e);
        }

        preglediMenu();
    }

    private void izmjenaPregleda(){

        Boolean nastaviPetlju = false;
        Integer index = null;
        do{
            try{
                kratkiIspisSvihPregleda();
                System.out.println("Izmjena pregleda: ");
                System.out.println("Unesite redni broj pregleda za koji želite unijeti izmjenu: ");
                System.out.print(">> ");
                index = unos.nextInt();
                unos.nextLine();
                nastaviPetlju = false;

            }catch (InputMismatchException e){
                System.out.println("Neispravan unos!");
                nastaviPetlju = true;
                logger.info(e.getMessage(), e);
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Unesen je redni broj za ne postojećeg pacijenta!");
                nastaviPetlju = true;
                unos.nextLine();
                logger.info(e.getMessage(), e);
            }catch (IznimkaNemaUpisanihPregleda e){
                System.out.println(e.getMessage());
                logger.info(e.getMessage(), e);
                preglediMenu();
            }
        }while(nastaviPetlju);



        Integer izborIzmjene = null;
        nastaviPetlju = false;
        do{

            try{
                System.out.println("Što želite izmjeniti?");
                System.out.println("1. Ime");
                System.out.println("2. Prezime");
                System.out.println("3. OIB");
                System.out.println("4. Datum pregleda");
                System.out.print(">> ");

                izborIzmjene = unos.nextInt();
                unos.nextLine();

                if(izborIzmjene < 1 || izborIzmjene > 4){
                    System.out.println("Neispravan unos!");
                    nastaviPetlju = true;
                }else{
                    nastaviPetlju = false;
                }

            }catch (InputMismatchException e) {
                System.out.println("Neispravan unos!");
                nastaviPetlju = true;
                unos.nextLine();
                logger.info(e.getMessage(), e);
            }
        }while(nastaviPetlju);


        switch(izborIzmjene){
            case 1:
                System.out.println("Unesite novo ime: ");
                System.out.print(">> ");
                database.getPregled(index-1).getPacijent().setIme(unos.nextLine());
                break;
            case 2:
                System.out.println("Unesite novo prezime: ");
                System.out.print(">> ");
                database.getPregled(index-1).getPacijent().setPrezime(unos.nextLine());
                break;
            case 3:
                System.out.println("Unesite novi OIB: ");
                System.out.print(">> ");
                database.getPregled(index-1).getPacijent().setOib(unos.nextLine());
                break;
            case 4:
                System.out.println("Unesite novi datum pregleda formata dd.MM.yyyy.: ");
                System.out.print(">> ");
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                LocalDate tempDatum = LocalDate.parse(unos.nextLine(), dateFormat);
                database.getPregled(index-1).setDatumPregleda(tempDatum);
                break;
        }

        System.out.println("Izmjene prihvaćene!");
        povratakNaPocetniMeni();

    }

    private void brisanjePregleda() throws IznimkaNemaUpisanihPregleda {
        System.out.println("Brisanje pregleda: ");
        kratkiIspisSvihPregleda();

        System.out.println("Unesite redni broj pregleda koji želite obrisati:");
        System.out.print(">> ");

        Integer izabranaOpcija = unos.nextInt();
        unos.nextLine();

        if(database.getPregledi().size() == 0){
            System.out.println("U bazi podataka nema niti jednog unesenog pregleda!");
            povratakNaPocetniMeni();
        }
        database.getPregledi().remove(izabranaOpcija-1);

        System.out.println("Pregled je izbrisan!");

        povratakNaPocetniMeni();

    }

    private void ispisPregleda(){
        try{
            kratkiIspisSvihPregleda();
            povratakNaPocetniMeni();
        }catch (IznimkaNemaUpisanihPregleda e){
            System.out.println(e.getMessage());
            logger.info(e.getMessage(), e);
            preglediMenu();
        }


    }


    private void povratakNaPocetniMeni(){
        System.out.println("Pritisnite enter za povratak nazad.");
        String enter = unos.nextLine();
        if(enter.length() == 0){
            System.out.println("Vracanje na pocetni menu...\n\n");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.info(e.getMessage(), e);
            }

            pocniProgram();
        }
    }

    private void kratkiIspisSvihPregleda() throws IznimkaNemaUpisanihPregleda{

        if(database.getPregledi().size() == 0){
            throw new IznimkaNemaUpisanihPregleda("Broj pregleda je jednak 0!");
        }

        System.out.println("Ime-Prezime-OIB-Datum");

        for(int i = 0;i<database.getPregledi().size();i++){
            System.out.println((i+1) + ". " + database.getPregled(i).getPacijent().getIme() + " " + database.getPregled(i).getPacijent().getPrezime() + " " + database.getPregled(i).getPacijent().getOib() + " " + database.getPregled(i).getDatumPregleda());
        }
    }


    public Integer getSljedeciKorak() {
        return sljedeciKorak;
    }

    public void setSljedeciKorak(Integer sljedeciKorak) {
        this.sljedeciKorak = sljedeciKorak;
    }

    public Integer getIndexKorisnika() {
        return indexKorisnika;
    }

    public void setIndexKorisnika(Integer indexKorisnika) {
        this.indexKorisnika = indexKorisnika;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
