package hu.szakdolgozat.tanya.exception;

public class ResourceNotFoundException extends TanyaException{

    public ResourceNotFoundException() {
        super("Nem található adat");
    }
}
