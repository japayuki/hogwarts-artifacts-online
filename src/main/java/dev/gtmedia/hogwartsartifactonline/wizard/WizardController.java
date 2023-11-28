package dev.gtmedia.hogwartsartifactonline.wizard;

import dev.gtmedia.hogwartsartifactonline.system.ResultResponse;
import dev.gtmedia.hogwartsartifactonline.wizard.dto.WizardDTOInternal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wizards")
public class WizardController {

    private final WizardService wizardService;

    public WizardController(WizardService wizardService) {
        this.wizardService = wizardService;
    }

    @GetMapping
    public ResultResponse findAllWizards(){
        List<WizardDTOInternal> allWizards = wizardService.findAllWizards();
        return new ResultResponse(true, 200, "All wizards fetched", allWizards);
    }

    @PutMapping("/{wizardId}/artifacts/{artifactId}")
    public ResultResponse assignArtifactToWizard(@PathVariable Integer wizardId, @PathVariable Integer artifactId){
        WizardDTOInternal wizardDTOInternal = wizardService.assignArtifactToWizard(wizardId, artifactId);
        return new ResultResponse(true, 200, "Artifact Assignment Successful", wizardDTOInternal);

    }
}
