package dev.gtmedia.hogwartsartifactonline.system;

import dev.gtmedia.hogwartsartifactonline.artifact.ArtifactRepository;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.HogwartsUser;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.UserService;
import dev.gtmedia.hogwartsartifactonline.wizard.Wizard;
import dev.gtmedia.hogwartsartifactonline.wizard.WizardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import dev.gtmedia.hogwartsartifactonline.artifact.Artifact;

@Component
public class DBInitializer implements CommandLineRunner {

    private final ArtifactRepository artifactRepository;
    private final WizardRepository wizardRepository;
    private final UserService userService;

    public DBInitializer(ArtifactRepository artifactRepository, WizardRepository wizardRepository, UserService userService) {
        this.artifactRepository = artifactRepository;
        this.wizardRepository = wizardRepository;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        Artifact a1 = new Artifact();
        a1.setId(1);
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");


        Artifact a2 = new Artifact();
        a2.setId(2);
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible.");



        Artifact a3 = new Artifact();
        a3.setId(3);
        a3.setName("Elder Wand");
        a3.setDescription("The Elder Wand, known throughout history as the Deathstick or the Wand of Destiny, is an extremely powerful wand made of elder wood with a core of Thestral tail hair.");


        Artifact a4 = new Artifact();
        a4.setId(4);
        a4.setName("The Marauder's Map");
        a4.setDescription("A magical map of Hogwarts created by Remus Lupin, Peter Pettigrew, Sirius Black, and James Potter while they were students at Hogwarts.");



        Artifact a5 = new Artifact();
        a5.setId(5);
        a5.setName("The Sword Of Gryffindor");
        a5.setDescription("A goblin-made sword adorned with large rubies on the pommel. It was once owned by Godric Gryffindor, one of the medieval founders of Hogwarts.");



        Artifact a6 = new Artifact();
        a6.setId(6);
        a6.setName("Resurrection Stone");
        a6.setDescription("The Resurrection Stone allows the holder to bring back deceased loved ones, in a semi-physical form, and communicate with them.");



        Wizard w1 = new Wizard();
        w1.setId(1);
        w1.setName("Albus Dumbledore");
        w1.addArtifact(a1);
        w1.addArtifact(a3);

        Wizard w2 = new Wizard();
        w2.setId(2);
        w2.setName("Harry Potter");
        w2.addArtifact(a2);
        w2.addArtifact(a4);

        Wizard w3 = new Wizard();
        w3.setId(3);
        w3.setName("Neville Longbottom");
        w3.addArtifact(a5);

        wizardRepository.save(w1);
        wizardRepository.save(w2);
        wizardRepository.save(w3);

        artifactRepository.save(a6);

//         Create some users.
        HogwartsUser u1 = new HogwartsUser();
        u1.setId(1);
        u1.setUsername("john");
        u1.setPassword("123456");
        u1.setEnabled(true);
        u1.setRoles("admin user");

        HogwartsUser u2 = new HogwartsUser();
        u2.setId(2);
        u2.setUsername("eric");
        u2.setPassword("654321");
        u2.setEnabled(true);
        u2.setRoles("user");

        HogwartsUser u3 = new HogwartsUser();
        u3.setId(3);
        u3.setUsername("tom");
        u3.setPassword("qwerty");
        u3.setEnabled(false);
        u3.setRoles("user");

        this.userService.addUser(u1);
        this.userService.addUser(u2);
        this.userService.addUser(u3);
    }
}
