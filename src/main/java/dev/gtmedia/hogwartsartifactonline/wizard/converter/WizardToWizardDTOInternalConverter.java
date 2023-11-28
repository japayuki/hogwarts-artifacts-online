package dev.gtmedia.hogwartsartifactonline.wizard.converter;

import dev.gtmedia.hogwartsartifactonline.artifact.Artifact;
import dev.gtmedia.hogwartsartifactonline.artifact.converter.ArtifactToArtifactDtoInternalConverter;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTOInternal;
import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import dev.gtmedia.hogwartsartifactonline.wizard.dto.WizardDTOInternal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WizardToWizardDTOInternalConverter implements Converter<Wizard, WizardDTOInternal> {

    private final ArtifactToArtifactDtoInternalConverter artifactToArtifactDtoInternalConverter;

    public WizardToWizardDTOInternalConverter(ArtifactToArtifactDtoInternalConverter artifactToArtifactDtoInternalConverter) {
        this.artifactToArtifactDtoInternalConverter = artifactToArtifactDtoInternalConverter;
    }

    @Override
    public WizardDTOInternal convert(Wizard source) {
        Set<Artifact> artifacts = source.getArtifacts();
        List<ArtifactDTOInternal> internalArtifacts = artifactToArtifactDtoInternalConverter.convert(new ArrayList<>(artifacts));
        return new WizardDTOInternal(source.getId(), source.getName(), internalArtifacts);
    }

    public List<WizardDTOInternal> convert(List<Wizard> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }
}
