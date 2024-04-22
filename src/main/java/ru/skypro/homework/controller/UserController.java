package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.generatedDto.NewPasswordDto;
import ru.skypro.homework.dto.generatedDto.UserDto;
//import ru.skypro.homework.entity.MyUser;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Operation(summary = "setPassword",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "403", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content)
            })
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        log.info("Was invoked set password for user method");
        authService.changePassword(newPasswordDto);
        System.out.println("sdfddd");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "getUser",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(
                                    schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(responseCode = "404", content = @Content)
            })

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        log.info("Was invoked get user method");
        return ResponseEntity.ok(userService.getAuth());
    }

    @Operation(summary = "updateUser",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(responseCode = "204", content = @Content),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "403", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content)
            })
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        log.info("Was invoked update user method");
        return ResponseEntity.ok(userService.updateUser(userDto));
    }
    @Operation(summary = "updateUserImage",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content)
            })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserAvatar(@RequestBody MultipartFile image) {
        log.info("Was invoked update user image method");
        userService.updateUserAvatar(userService.getAuth().getEmail(), image);
        return ResponseEntity.ok().build();
    }
}
