package dev.gtmedia.hogwartsartifactonline.exception;

public class ArtifactNotFoundException extends RuntimeException{

    public ArtifactNotFoundException(Integer id) {
        super("Artifact with id "+id+" not found");
    }
}
