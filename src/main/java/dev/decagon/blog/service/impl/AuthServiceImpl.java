package dev.decagon.blog.service.impl;

import dev.decagon.blog.entity.Role;
import dev.decagon.blog.entity.User;
import dev.decagon.blog.payload.LoginDto;
import dev.decagon.blog.payload.SignupDto;
import dev.decagon.blog.repository.RoleRepository;
import dev.decagon.blog.repository.UserRepository;
import dev.decagon.blog.security.JwtTokenProvider;
import dev.decagon.blog.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public String authenticateUser(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return tokenProvider.generateToken(authenticate);
    }

    @Override
    public ResponseEntity<?> registerUser(SignupDto signupDto) {
        if(userRepository.existsByUsername(signupDto.getUsername())){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signupDto.getEmail())){
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signupDto.getName());
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").orElseThrow();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
