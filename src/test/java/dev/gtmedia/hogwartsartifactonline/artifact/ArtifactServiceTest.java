package dev.gtmedia.hogwartsartifactonline.artifact;

import dev.gtmedia.hogwartsartifactonline.exception.ObjectNotFoundException;
import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock
    ArtifactRepository artifactRepository;

    @InjectMocks
    ArtifactService artifactService;

    List<Artifact> artifacts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Artifact a1 = new Artifact();
        a1.setId(1);
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");
        artifacts.add(a1);

        Artifact a2 = new Artifact();
        a2.setId(2);
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible.");
        artifacts.add(a2);


        Artifact a3 = new Artifact();
        a3.setId(3);
        a3.setName("Elder Wand");
        a3.setDescription("The Elder Wand, known throughout history as the Deathstick or the Wand of Destiny, is an extremely powerful wand made of elder wood with a core of Thestral tail hair.");
        artifacts.add(a3);

        Artifact a4 = new Artifact();
        a4.setId(4);
        a4.setName("The Marauder's Map");
        a4.setDescription("A magical map of Hogwarts created by Remus Lupin, Peter Pettigrew, Sirius Black, and James Potter while they were students at Hogwarts.");
        artifacts.add(a4);


        Artifact a5 = new Artifact();
        a5.setId(5);
        a5.setName("The Sword Of Gryffindor");
        a5.setDescription("A goblin-made sword adorned with large rubies on the pommel. It was once owned by Godric Gryffindor, one of the medieval founders of Hogwarts.");
        artifacts.add(a5);


        Artifact a6 = new Artifact();
        a6.setId(6);
        a6.setName("Resurrection Stone");
        a6.setDescription("The Resurrection Stone allows the holder to bring back deceased loved ones, in a semi-physical form, and communicate with them.");
        artifacts.add(a6);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByIdSuccess() {
        //Given. Arrange inputs and targets. Define behavior of Mock objects
        Wizard w = new Wizard(1, "Harry Potter",null);
        Artifact a = new Artifact(1,"Invisibility cloak", "A cloak the makes the user invisible", w);

        given(artifactRepository.findById(1)).willReturn(Optional.of(a));
        //When. Call the method to be tested
        Artifact artifact = artifactService.findById(1);
        //Then. Confirm results via assertions

        assertThat(artifact.getId()).isEqualTo(a.getId());
        assertThat(artifact.getDescription()).isEqualTo(a.getDescription());
        assertThat(artifact.getOwner()).isEqualTo(a.getOwner());
        verify(artifactRepository, times(1)).findById(1);
    }

    @Test
    void findByIdNotFound(){
        //Given
        given(artifactRepository.findById(Mockito.anyInt())).willReturn(Optional.empty());
        //When
        Throwable throwable = catchThrowable(()-> {
            artifactService.findById(1);
        });
        //Then
        assertThat(throwable).isInstanceOf(ObjectNotFoundException.class).hasMessage("Artifact with id 1 not found");
        verify(artifactRepository, times(1)).findById(1);
    }

    @Test
    void findAll() {
        given(artifactRepository.findAll()).willReturn(this.artifacts);

        List<Artifact> actualArtifacts = artifactService.findAll();

        assertThat(actualArtifacts.size()).isEqualTo(this.artifacts.size());
        verify(artifactRepository, times(1)).findAll();
    }
}