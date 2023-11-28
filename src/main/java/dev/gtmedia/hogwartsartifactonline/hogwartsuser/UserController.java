package dev.gtmedia.hogwartsartifactonline.hogwartsuser;

import dev.gtmedia.hogwartsartifactonline.hogwartsuser.dto.UserDto;
import dev.gtmedia.hogwartsartifactonline.system.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResultResponse findAllUsers(){
        List<UserDto> allUsers = userService.findAll();
        return new ResultResponse(true, 200, "Fetch All Users Success", allUsers);
    }

    @GetMapping("/{userId}")
    public ResultResponse findUserById(@PathVariable Integer userId){
        UserDto user = userService.findUserById(userId);
        return new ResultResponse(true, 200, "Find User Success",user);
    }

    @Operation(
            description = "Description",
            summary = "Summary",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping
    public ResultResponse addUser(@RequestBody @Valid HogwartsUser hogwartsUser){
        UserDto userDto = userService.addUser(hogwartsUser);
        return new ResultResponse(true, 200, "Add User Success", userDto);
    }
}
