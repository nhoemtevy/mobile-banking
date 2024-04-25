package com.mobilebankingapi.feature.auth;


import com.mobilebankingapi.feature.auth.dto.AuthResponse;
import com.mobilebankingapi.feature.auth.dto.LoginRequest;
import com.mobilebankingapi.feature.auth.dto.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refresh(refreshTokenRequest);
    }

    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return authService.login(loginRequest);
    }

}
