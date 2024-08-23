package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.LoginRequest;
import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.mapper.JwtResponse;
import com.udemy.elearning.mapper.UserResponse;
import com.udemy.elearning.models.User;
import com.udemy.elearning.services.AuthenticationService;
import com.udemy.elearning.services.JwtService;
import jakarta.validation.Valid;

import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/welcome2")
    public String welcome2() {
        return "Welcome2 this endpoint is not secure";
    }

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody LoginRequest loginUserDto) {
        logger.info("loginUserDto{}", loginUserDto);

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        JwtResponse loginResponse = new JwtResponse (jwtToken,jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody SignupRequest registerUserDto) throws BadRequestException {
        User registeredUser = authenticationService.signup(registerUserDto);
        UserResponse userResponse = new UserResponse(registeredUser);
        logger.info("registerUserDto{}", registerUserDto);

        return ResponseEntity.ok(userResponse);
    }

}
