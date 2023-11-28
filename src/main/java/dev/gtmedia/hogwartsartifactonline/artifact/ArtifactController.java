package dev.gtmedia.hogwartsartifactonline.artifact;

import dev.gtmedia.hogwartsartifactonline.artifact.converter.ArtifactToArtifactDtoConverter;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import dev.gtmedia.hogwartsartifactonline.system.ResultResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artifacts")
public class ArtifactController {

    private final ArtifactService artifactService;
    private final ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter;

    public ArtifactController(ArtifactService artifactService, ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter) {
        this.artifactService = artifactService;
        this.artifactToArtifactDtoConverter = artifactToArtifactDtoConverter;
    }

    @GetMapping
    public ResultResponse findAllArtifacts(){
        List<Artifact> all = artifactService.findAll();
        return new ResultResponse(true, 200, "Find All Success", artifactToArtifactDtoConverter.convert(all));
    }

    @GetMapping("/{artifactId}")
    public ResultResponse findArtifactById(@PathVariable Integer artifactId){
        Artifact foundArtifact = artifactService.findById(artifactId);
        return new ResultResponse(true, 200, "Find One Success", artifactToArtifactDtoConverter.convert(foundArtifact));
    }

    @PostMapping
    public ResultResponse addArtifact(@RequestBody @Valid ArtifactDTO artifactDTO){
        Artifact savedArtifact = artifactService.save(artifactDTO);
        return new ResultResponse(true, 200, "Add Artifact Success", artifactToArtifactDtoConverter.convert(savedArtifact));

    }

    @PutMapping()
    public ResultResponse updateArtifact(@RequestBody @Valid ArtifactDTO artifactDTO){
        Artifact updatedArtifact = artifactService.update(artifactDTO);
        return new ResultResponse(true, 200, "Update Artifact Success", artifactToArtifactDtoConverter.convert(updatedArtifact));
    }

    @DeleteMapping("/{artifactId}")
    public ResultResponse deleteArtifactById(@PathVariable Integer artifactId){
        boolean status = artifactService.deleteById(artifactId);
        return new ResultResponse(status, 200, "Delete Artifact Success", null);
    }


}
