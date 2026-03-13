package com.bilbioteca.pruebaTecnicaBiblioteca.auth;

import com.bilbioteca.pruebaTecnicaBiblioteca.dtos.AuthResponseDto;
import com.bilbioteca.pruebaTecnicaBiblioteca.dtos.LoginRequestDto;
import com.bilbioteca.pruebaTecnicaBiblioteca.dtos.RegisterRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterRequestDto request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto request){
        return authService.login(request);
    }
}
