package dev.gtmedia.hogwartsartifactonline.artifact;

import org.springframework.data.jpa.domain.Specification;

public class ArtifactSpecifications {
    public static Specification<Artifact> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
                "%"+name.toUpperCase()+"%");
    }

    public static Specification<Artifact> hasDescription(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("description")),
                "%"+description.toUpperCase()+"%");
    }

}

