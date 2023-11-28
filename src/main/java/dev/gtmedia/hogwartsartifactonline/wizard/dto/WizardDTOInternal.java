package dev.gtmedia.hogwartsartifactonline.wizard.dto;

import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTOInternal;

import java.util.List;

public record WizardDTOInternal(Integer id, String name, List<ArtifactDTOInternal> artifacts) {
}
