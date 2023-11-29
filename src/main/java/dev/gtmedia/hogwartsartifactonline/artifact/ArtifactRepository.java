package dev.gtmedia.hogwartsartifactonline.artifact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArtifactRepository extends JpaRepository<Artifact, Integer>, JpaSpecificationExecutor<Artifact> {
}
