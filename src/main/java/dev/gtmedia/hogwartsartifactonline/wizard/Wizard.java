package dev.gtmedia.hogwartsartifactonline.wizard;

import dev.gtmedia.hogwartsartifactonline.artifact.Artifact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Wizard implements Serializable {

    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Artifact> artifacts = new HashSet<>();

    public Wizard(Integer id, String name, Set<Artifact> artifacts) {
        this.id = id;
        this.name = name;
        this.artifacts = artifacts;
    }

    public Wizard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(Set<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public void addArtifact(Artifact artifact) {
        artifact.setOwner(this);
        artifacts.add(artifact);
    }

    public Integer getNumOfArtifacts() {
        return artifacts.size();
    }
}
