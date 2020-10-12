package hu.szakdolgozat.tanya.web.exception;

public class ResourceNotFoundException extends TanyaException{

    public ResourceNotFoundException() {
        super("Nem található adat");
    }
}
