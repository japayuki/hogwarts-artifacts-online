package dev.gtmedia.hogwartsartifactonline.security;

import dev.gtmedia.hogwartsartifactonline.system.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResultResponse getLoginInfo(Authentication authentication){
        logger.debug("Authenticated user: '{}'", authentication.getName());
        Map<String, Object> loginInfo = authService.createLoginInfo(authentication);
        return new ResultResponse(true, 200, "User Info and JSON Web Token", loginInfo);
    }

}
