package dev.gtmedia.hogwartsartifactonline.artifact.converter;

import dev.gtmedia.hogwartsartifactonline.artifact.Artifact;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTOInternal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtifactToArtifactDtoInternalConverter implements Converter<Artifact, ArtifactDTOInternal> {
    @Override
    public ArtifactDTOInternal convert(Artifact source) {
        return new ArtifactDTOInternal(source.getId(), source.getName(), source.getDescription() );
    }

    public List<ArtifactDTOInternal> convert(List<Artifact> source) {
        return source.stream().map(a -> new ArtifactDTOInternal(a.getId(), a.getName(), a.getDescription() )).toList();
    }
}
