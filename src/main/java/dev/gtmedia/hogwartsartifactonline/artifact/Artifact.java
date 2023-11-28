package dev.gtmedia.hogwartsartifactonline.artifact;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Artifact implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String description;

    @ManyToOne
    private Wizard owner;

    public Artifact(Integer id, String name, String description, Wizard owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public Artifact() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wizard getOwner() {
        return owner;
    }

    public void setOwner(Wizard owner) {
        this.owner = owner;
    }
}
