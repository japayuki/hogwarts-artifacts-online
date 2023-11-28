package dev.gtmedia.hogwartsartifactonline.artifact.dto;

import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import dev.gtmedia.hogwartsartifactonline.wizard.dto.WizardDTO;
import jakarta.validation.constraints.NotEmpty;

public record ArtifactDTO(Integer id,
                          @NotEmpty(message = "name is required") String name,
                          @NotEmpty(message = "description is required") String description,
                          WizardDTO owner) {
}
