package dev.gtmedia.hogwartsartifactonline.hogwartsuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<HogwartsUser, Integer> {
}
