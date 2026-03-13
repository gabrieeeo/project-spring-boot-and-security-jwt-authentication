package me.gabriel.authentication.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.gabriel.authentication.model.UserModel;
import me.gabriel.authentication.model.dto.RegisterDTO;
import me.gabriel.authentication.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/auth", produces = {"application/json"})
public class AuthenticationController {

    private final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.username()))  {
            return ResponseEntity.badRequest().body("Username is already in use.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel userModel = new UserModel(registerDTO.username(), registerDTO.email(), encryptedPassword, registerDTO.role());
        userRepository.save(userModel);

        return ResponseEntity.ok().body("User registered successfully.");
        
    }
}
