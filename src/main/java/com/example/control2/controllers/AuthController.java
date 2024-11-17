package com.example.control2.controllers;

import com.example.control2.config.JwtUtil;
import com.example.control2.dto.LoginDto;
import com.example.control2.dto.RegisterDto;
import com.example.control2.models.User;
import com.example.control2.services.CustomUserDetailsService;
import com.example.control2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginDto loginDto) {
        try { // Intentar autenticar al usuario
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(login);

            // Si la autenticación fue exitosa, crear un JWT y devolverlo en el header
            String jwt = this.jwtUtil.create(loginDto.getUsername());

            User user = userService.getUserByUsername(loginDto.getUsername()); // Asume que el usuario es una instancia de org.springframework.security.core.userdetails.User
            Long userId = user.getUserid();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwt)
                    .body(userId);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        User newUser = new User();
        newUser.setUsername(registerDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword())); // Encriptar la contraseña
        newUser.setRol(registerDto.getRol());
        newUser.setEmail(registerDto.getEmail());
        newUser.setName(registerDto.getName());

        userService.createUser(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Access granted");
    }

}