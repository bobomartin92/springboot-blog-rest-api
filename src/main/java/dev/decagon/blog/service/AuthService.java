package dev.decagon.blog.service;

import dev.decagon.blog.payload.LoginDto;
import dev.decagon.blog.payload.SignupDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String authenticateUser(LoginDto loginDto);

    ResponseEntity<?> registerUser(SignupDto signupDto);
}
