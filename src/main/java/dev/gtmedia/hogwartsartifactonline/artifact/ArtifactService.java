package dev.gtmedia.hogwartsartifactonline.artifact;

import dev.gtmedia.hogwartsartifactonline.artifact.converter.ArtifactToArtifactDtoConverter;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import dev.gtmedia.hogwartsartifactonline.exception.ArtifactNotFoundException;
import dev.gtmedia.hogwartsartifactonline.exception.WizardNotFoundException;
import dev.gtmedia.hogwartsartifactonline.utils.IdWorker;
import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import dev.gtmedia.hogwartsartifactonline.wizard.WizardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
        return artifactRepository.findById(artifactId).orElseThrow(() -> new ArtifactNotFoundException(artifactId));
    }

    public Artifact update(ArtifactDTO artifactDTO){
        return artifactRepository.findById(artifactDTO.id()).map(artifact->{
            artifact.setName(artifactDTO.name());
            artifact.setDescription(artifactDTO.description());
            if (artifactDTO.owner() != null){
                Integer ownerId = artifactDTO.owner().id();
                Wizard wizard = wizardRepository.findById(ownerId).orElseThrow(() -> new WizardNotFoundException(ownerId));
                wizard.addArtifact(artifact);
            }
            return artifactRepository.save(artifact);
        }).orElseThrow(() -> new ArtifactNotFoundException(artifactDTO.id()));
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
        artifactRepository.deleteById(artifactId);
        return true;
    }
}
