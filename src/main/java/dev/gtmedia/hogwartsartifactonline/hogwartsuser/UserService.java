package dev.gtmedia.hogwartsartifactonline.hogwartsuser;

import dev.gtmedia.hogwartsartifactonline.exception.ObjectNotFoundException;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.converter.HogwartsUserToUserDTOConverter;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.dto.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final HogwartsUserToUserDTOConverter converter;

    public UserService(UserRepository userRepository, HogwartsUserToUserDTOConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }


    public List<UserDto> findAll() {
        List<HogwartsUser> allUsers = userRepository.findAll();
        return converter.convert(allUsers);
    }

    public UserDto addUser(HogwartsUser hogwartsUser) {
        HogwartsUser savedUser = userRepository.save(hogwartsUser);
        return converter.convert(savedUser);
    }

    public UserDto findUserById(Integer userId) {
        HogwartsUser user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(userId, "User"));
        return converter.convert(user);
    }
}
