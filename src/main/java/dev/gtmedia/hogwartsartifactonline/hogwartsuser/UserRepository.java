package dev.gtmedia.hogwartsartifactonline.hogwartsuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface UserRepository extends JpaRepository<HogwartsUser, Integer> {
    Optional<HogwartsUser> findUserByUsername(String username);

}
