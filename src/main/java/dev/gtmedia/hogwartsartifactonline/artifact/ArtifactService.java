package dev.gtmedia.hogwartsartifactonline.artifact;

import dev.gtmedia.hogwartsartifactonline.artifact.converter.ArtifactToArtifactDtoConverter;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import dev.gtmedia.hogwartsartifactonline.exception.ObjectNotFoundException;
import dev.gtmedia.hogwartsartifactonline.utils.IdWorker;
import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import dev.gtmedia.hogwartsartifactonline.wizard.WizardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;
    private final ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter;
    private final WizardRepository wizardRepository;
    private final IdWorker idWorker;

    public ArtifactService(ArtifactRepository artifactRepository, ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter, WizardRepository wizardRepository, IdWorker idWorker) {
        this.artifactRepository = artifactRepository;
        this.artifactToArtifactDtoConverter = artifactToArtifactDtoConverter;
        this.wizardRepository = wizardRepository;
        this.idWorker = idWorker;
    }

    public Artifact findById(Integer artifactId){
        return artifactRepository.findById(artifactId).orElseThrow(() -> new ObjectNotFoundException(artifactId, "Artifact"));
    }

    public Artifact update(ArtifactDTO artifactDTO){
        return artifactRepository.findById(artifactDTO.id()).map(artifact->{
            artifact.setName(artifactDTO.name());
            artifact.setDescription(artifactDTO.description());
            if (artifactDTO.owner() != null){
                Integer ownerId = artifactDTO.owner().id();
                Wizard wizard = wizardRepository.findById(ownerId).orElseThrow(() -> new ObjectNotFoundException(ownerId, "Wizard"));
                wizard.addArtifact(artifact);
            }
            return artifactRepository.save(artifact);
        }).orElseThrow(() -> new ObjectNotFoundException(artifactDTO.id(),"Artifact"));
    }

    public List<Artifact> findAll() {
        return artifactRepository.findAll();
    }

    public Artifact save(ArtifactDTO artifactDTO) {
        Artifact artifact = artifactToArtifactDtoConverter.convert(artifactDTO);
        artifact.setId(Long.valueOf(idWorker.nextId()).intValue());
        return artifactRepository.save(artifact);
    }

    public boolean deleteById(Integer artifactId) {
        findById(artifactId); //Will throw if artifact not found
        artifactRepository.deleteById(artifactId);
        return true;
    }

    public ArtifactDTO unassignArtifact(Integer artifactId) {
        Artifact artifact = findById(artifactId);
        artifact.setOwner(null);
        Artifact savedArtifact = artifactRepository.save(artifact);
        return artifactToArtifactDtoConverter.convert(savedArtifact);
    }
}
