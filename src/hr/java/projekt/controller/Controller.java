package hr.java.projekt.controller;

import hr.java.projekt.database.Database;
import hr.java.projekt.entitet.Pacijent;
import hr.java.projekt.entitet.Pregled;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Controller {


    private Integer sljedeciKorak;
    private Integer indexKorisnika;
    private Scanner unos;
    private Database database;

    public Controller(Scanner unos, Integer indexKorisnika, Database database) {
        this.unos = unos;
        this.indexKorisnika = indexKorisnika;
        this.database = database;
    }

    public void pocniProgram(){
        redirectController(odabirOpcije(unos));
    }

    public static Integer odabirOpcije(Scanner unos){

        System.out.println("Odaberite jednu od ponuđenih opcija: ");

        System.out.println("1. Pregledi");

        Integer temp = unos.nextInt();
        unos.nextLine();

        return temp;
    }

    private void redirectController(Integer broj){
        switch (broj){
            case 1:
                preglediMenu();
                break;
            //TO DO više menija
            default:
                System.out.println("Nije unesen ispravan broj!");
        }
    }

    private void preglediMenu(){
        System.out.println("Odaberite jednu od ponuđenih opcija: ");
        System.out.println("1. Unos pregleda");
        System.out.println("2. Izmjena pregleda");
        System.out.println("3. Brisanje pregleda");
        System.out.println("4. Ispis svih pregleda");
        System.out.print(">>  ");

        Integer temp = unos.nextInt();
        unos.nextLine();

        switch(temp){
            case 1:
                unosPregleda();
                break;
            case 2:
                izmjenaPregleda();
                break;
            case 3:
                brisanjePregleda();
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

        System.out.println("Pregled unesen!");
        System.out.println("Vracanje na pocetni menu...\n\n");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        preglediMenu();
    }

    private void izmjenaPregleda(){
        System.out.println("Izmjena pregleda: ");

        kratkiIspisSvihPregleda();

        System.out.println("Unesite redni broj pregleda za koji želite unijeti izmjenu: ");
        System.out.print(">> ");
        Integer index = unos.nextInt();
        unos.nextLine();

        System.out.println("Što želite izmjeniti?");
        System.out.println("1. Ime");
        System.out.println("2. Prezime");
        System.out.println("3. OIB");
        System.out.println("4. Datum pregleda");
        System.out.print(">> ");
        Integer izborIzmjene = unos.nextInt();
        unos.nextLine();

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

    private void brisanjePregleda(){
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
        kratkiIspisSvihPregleda();
        povratakNaPocetniMeni();

    }


    private void povratakNaPocetniMeni(){
        System.out.println("Pritisnite enter za povratak nazad.");
        String enter = unos.nextLine();
        if(enter.length() == 0){
            System.out.println("Vracanje na pocetni menu...\n\n");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            pocniProgram();
        }
    }

    private void kratkiIspisSvihPregleda(){
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
}
