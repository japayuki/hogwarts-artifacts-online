package dev.gtmedia.hogwartsartifactonline.wizard;

import dev.gtmedia.hogwartsartifactonline.artifact.Artifact;
import dev.gtmedia.hogwartsartifactonline.artifact.ArtifactRepository;
import dev.gtmedia.hogwartsartifactonline.exception.ObjectNotFoundException;
import dev.gtmedia.hogwartsartifactonline.wizard.converter.WizardToWizardDTOInternalConverter;
import dev.gtmedia.hogwartsartifactonline.wizard.dto.WizardDTOInternal;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WizardService {

    private final WizardRepository wizardRepository;
    private final WizardToWizardDTOInternalConverter wizardToWizardDTOInternalConverter;
    private final ArtifactRepository artifactRepository;

    public WizardService(WizardRepository wizardRepository, WizardToWizardDTOInternalConverter wizardToWizardDTOInternalConverter, ArtifactRepository artifactRepository) {
        this.wizardRepository = wizardRepository;
        this.wizardToWizardDTOInternalConverter = wizardToWizardDTOInternalConverter;
        this.artifactRepository = artifactRepository;
    }

    public List<WizardDTOInternal> findAllWizards(){
        List<Wizard> allWizards = wizardRepository.findAll();
        return wizardToWizardDTOInternalConverter.convert(allWizards);
    }

//    public List<Wizard> findAll() {
//        return this.wizardRepository.findAll();
//    }

    public WizardDTOInternal assignArtifactToWizard(Integer wizardId, Integer artifactId) {
        Wizard wizard = wizardRepository.findById(wizardId).orElseThrow(()-> new ObjectNotFoundException(wizardId,"Wizard"));
        Artifact artifact = artifactRepository.findById(artifactId).orElseThrow(()-> new ObjectNotFoundException(artifactId,"Artifact"));
        wizard.addArtifact(artifact);
        wizardRepository.save(wizard);
        return wizardToWizardDTOInternalConverter.convert(wizard);
    }
}
