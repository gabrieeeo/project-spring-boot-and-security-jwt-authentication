package me.gabriel.authentication.controller;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.gabriel.authentication.config.TokenService;
import me.gabriel.authentication.model.UserModel;
import me.gabriel.authentication.model.dto.AuthenticationDTO;
import me.gabriel.authentication.model.dto.LoginResponseDTO;
import me.gabriel.authentication.model.dto.RegisterDTO;
import me.gabriel.authentication.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/auth", produces = { "application/json" })
public class AuthenticationController {

    private final UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public AuthenticationController(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /** 
     * @param registerDTO
     * @return ResponseEntity<String>
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.username())) {
            return ResponseEntity.badRequest().body("Username is already in use.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel userModel = new UserModel(registerDTO.username(), registerDTO.email(), encryptedPassword,
                registerDTO.role());
        userRepository.save(userModel);

        return ResponseEntity.ok().body("User registered successfully.");

    }

    /** 
     * @param authenticationDTO
     * @return ResponseEntity<LoginResponseDTO>
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> authentication(@RequestBody AuthenticationDTO authenticationDTO) {
        var credentials = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(credentials);
        String token = tokenService.generateToken((UserModel) auth.getPrincipal());
        LocalDateTime expirationTime = tokenService.getExpirationTime(token);

        return ResponseEntity.ok(new LoginResponseDTO(token, expirationTime));
    }
}
