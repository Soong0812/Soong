package user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import user.dto.UserRequestDto;
import user.dto.UserResponseDto;
import user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public UserResponseDto createUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/user")
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public UserResponseDto getUser(
            @PathVariable("userId") Long userId
    ) {
        return userService.getUser(userId);
    }

    @PutMapping("/user/{userId}")
    public UserResponseDto updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.updateUser(userId, userRequestDto);
    }

    @DeleteMapping
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
