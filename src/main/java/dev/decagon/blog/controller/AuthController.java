package dev.decagon.blog.controller;

import dev.decagon.blog.payload.JWTAuthResponse;
import dev.decagon.blog.payload.LoginDto;
import dev.decagon.blog.payload.SignupDto;
import dev.decagon.blog.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){

        String token = authService.authenticateUser(loginDto);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signupDto){

        return authService.registerUser(signupDto);
    }
}
