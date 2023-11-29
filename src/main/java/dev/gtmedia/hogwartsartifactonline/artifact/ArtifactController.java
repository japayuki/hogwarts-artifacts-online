package dev.gtmedia.hogwartsartifactonline.artifact;

import dev.gtmedia.hogwartsartifactonline.artifact.converter.ArtifactToArtifactDtoConverter;
import dev.gtmedia.hogwartsartifactonline.artifact.dto.ArtifactDTO;
import dev.gtmedia.hogwartsartifactonline.system.ResultResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/query")
    public ResultResponse query(
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<ArtifactDTO> artifacts = artifactService.findArtifactsByQuery(name, description, pageable);
        return new ResultResponse(true, 200, "Query Success", artifacts);
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

    @PutMapping("/{artifactId}")
    public ResultResponse unassignArtifact(@PathVariable Integer artifactId){
        ArtifactDTO artifactDTO = artifactService.unassignArtifact(artifactId);
        return new ResultResponse(true, 200, "Artifact Unassignment Success", artifactDTO);
    }

    @DeleteMapping("/{artifactId}")
    public ResultResponse deleteArtifactById(@PathVariable Integer artifactId){
        boolean status = artifactService.deleteById(artifactId);
        return new ResultResponse(status, 200, "Delete Artifact Success", null);
    }


}
