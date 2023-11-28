package dev.gtmedia.hogwartsartifactonline.config;

import dev.gtmedia.hogwartsartifactonline.artifact.converter.ArtifactToArtifactDtoConverter;
import dev.gtmedia.hogwartsartifactonline.utils.IdWorker;
import dev.gtmedia.hogwartsartifactonline.wizard.converter.WizardToWizardDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
