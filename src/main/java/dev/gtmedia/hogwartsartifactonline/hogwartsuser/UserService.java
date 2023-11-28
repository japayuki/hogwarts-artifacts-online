package dev.gtmedia.hogwartsartifactonline.hogwartsuser;

import dev.gtmedia.hogwartsartifactonline.exception.ObjectNotFoundException;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.converter.HogwartsUserToUserDTOConverter;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.dto.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HogwartsUserToUserDTOConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, HogwartsUserToUserDTOConverter converter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UserDto> findAll() {
        List<HogwartsUser> allUsers = userRepository.findAll();
        return converter.convert(allUsers);
    }

    public UserDto addUser(HogwartsUser hogwartsUser) {
        hogwartsUser.setPassword(passwordEncoder.encode(hogwartsUser.getPassword()));
        userRepository.save(hogwartsUser);
        return converter.convert(hogwartsUser);
    }

    public UserDto findUserById(Integer userId) {
        HogwartsUser user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(userId, "User"));
        return converter.convert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password is incorrect"));
    }
}
