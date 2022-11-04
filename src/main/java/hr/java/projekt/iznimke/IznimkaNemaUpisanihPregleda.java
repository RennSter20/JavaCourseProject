package hr.java.projekt.iznimke;

public class IznimkaNemaUpisanihPregleda extends Exception {

    public IznimkaNemaUpisanihPregleda() {
    }

    public IznimkaNemaUpisanihPregleda(String message) {
        super(message);
    }

    public IznimkaNemaUpisanihPregleda(String message, Throwable cause) {
        super(message, cause);
    }

    public IznimkaNemaUpisanihPregleda(Throwable cause) {
        super(cause);
    }
}
