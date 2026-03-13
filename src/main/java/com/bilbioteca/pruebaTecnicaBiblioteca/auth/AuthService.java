package com.bilbioteca.pruebaTecnicaBiblioteca.auth;

import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.User;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.enums.ROLE;
import com.bilbioteca.pruebaTecnicaBiblioteca.dtos.AuthResponseDto;
import com.bilbioteca.pruebaTecnicaBiblioteca.dtos.LoginRequestDto;
import com.bilbioteca.pruebaTecnicaBiblioteca.dtos.RegisterRequestDto;
import com.bilbioteca.pruebaTecnicaBiblioteca.repository.UserRepository;
import com.bilbioteca.pruebaTecnicaBiblioteca.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authManager){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }
    public AuthResponseDto register(RegisterRequestDto request){

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(ROLE.STUDENT);

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto request){

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(request.getUsername());

        return new AuthResponseDto(token);
    }



}
