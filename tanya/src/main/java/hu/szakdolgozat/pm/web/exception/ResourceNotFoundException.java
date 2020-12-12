package hu.szakdolgozat.pm.web.exception;

public class ResourceNotFoundException extends ProjectManagerException {

    public ResourceNotFoundException() {
        super("Adat nem található adat");
    }
}
