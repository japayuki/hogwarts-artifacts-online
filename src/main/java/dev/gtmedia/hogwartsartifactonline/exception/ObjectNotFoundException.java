package dev.gtmedia.hogwartsartifactonline.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(Integer id, String objectName) {
        super("Could not find " + objectName + " with id " + id);
    }
}
