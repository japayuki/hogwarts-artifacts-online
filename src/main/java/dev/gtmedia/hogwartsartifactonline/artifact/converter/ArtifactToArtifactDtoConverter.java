package dev.gtmedia.hogwartsartifactonline.artifact.converter;

import dev.gtmedia.hogwartsartifactonline.artifact.Artifact;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import dev.gtmedia.hogwartsartifactonline.wizard.converter.WizardToWizardDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtifactToArtifactDtoConverter implements Converter<Artifact, ArtifactDTO> {

    private final WizardToWizardDtoConverter wizardToWizardDtoConverter;

    public ArtifactToArtifactDtoConverter(WizardToWizardDtoConverter wizardToWizardDtoConverter) {
        this.wizardToWizardDtoConverter = wizardToWizardDtoConverter;
    }

    @Override
    public ArtifactDTO convert(Artifact source) {
        return new ArtifactDTO(source.getId(),
                source.getName(),
                source.getDescription(),
                source.getOwner() != null
                        ? wizardToWizardDtoConverter.convert(source.getOwner())
                        : null);
    }

    public Artifact convert(ArtifactDTO source) {
        return new Artifact(source.id(),
                source.name(),
                source.description(),
                source.owner() != null
                        ? wizardToWizardDtoConverter.convert(source.owner())
                        : null);
    }


    public List<ArtifactDTO> convert(List<Artifact> source) {
        return source.stream().map(a -> new ArtifactDTO(a.getId(),
                a.getName(),
                a.getDescription(),
                a.getOwner() != null
                        ? wizardToWizardDtoConverter.convert(a.getOwner())
                        : null)).toList();
    }
}
