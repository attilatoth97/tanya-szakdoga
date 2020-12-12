package hu.szakdolgozat.pm.web.exception;

import lombok.Data;

public class ProjectManagerValidationException extends ProjectManagerException{

    public ProjectManagerValidationException(String errorMessage) {
        super(errorMessage);
    }
}
