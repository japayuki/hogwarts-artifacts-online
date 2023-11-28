package dev.gtmedia.hogwartsartifactonline.exception;

public class WizardNotFoundException extends RuntimeException{
    public WizardNotFoundException(Integer id) {
        super("Wizard with id "+ id + " not found" );
    }
}
