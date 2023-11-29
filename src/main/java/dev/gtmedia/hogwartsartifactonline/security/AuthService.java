package dev.gtmedia.hogwartsartifactonline.security;

import dev.gtmedia.hogwartsartifactonline.hogwartsuser.HogwartsUser;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.UserPrincipal;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.converter.HogwartsUserToUserDTOConverter;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.dto.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AuthService {

    private final JwtProvider jwtProvider;
    private final HogwartsUserToUserDTOConverter converter;

    public AuthService(JwtProvider jwtProvider, HogwartsUserToUserDTOConverter converter) {
        this.jwtProvider = jwtProvider;
        this.converter = converter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        //create user info
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        HogwartsUser hogwartsUser = principal.hogwartsUser();
        UserDto userDto = converter.convert(hogwartsUser);
        //create JWT
        String token = jwtProvider.createToken(authentication);
//        String token = "fdsf23423df2q";
        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);
        return loginResultMap;
    }
}
