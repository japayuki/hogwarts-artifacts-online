package dev.gtmedia.hogwartsartifactonline.wizard.converter;

import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import dev.gtmedia.hogwartsartifactonline.wizard.dto.WizardDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WizardToWizardDtoConverter implements Converter<Wizard, WizardDTO> {

    @Override
    public WizardDTO convert(Wizard wizard) {
        return new WizardDTO(wizard.getId(), wizard.getName(), wizard.getNumOfArtifacts());
    }

    public Wizard convert(WizardDTO wizardDTO) {
        return new Wizard(wizardDTO.id(), wizardDTO.name(), null);
    }

    @Override
    public <U> Converter<Wizard, U> andThen(Converter<? super WizardDTO, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
