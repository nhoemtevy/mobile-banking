package com.mobilebankingapi.feature.auth;


import com.mobilebankingapi.feature.auth.dto.AuthResponse;
import com.mobilebankingapi.feature.auth.dto.ChangePasswordRequest;
import com.mobilebankingapi.feature.auth.dto.LoginRequest;
import com.mobilebankingapi.feature.auth.dto.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PutMapping("/change-password")
    void changePassword (@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
                         @AuthenticationPrincipal Jwt jwt){
        System.out.println(jwt.getClaims());
        System.out.println(changePasswordRequest.confirmedPassword());
        System.out.println(changePasswordRequest.newPassword());
      authService.changePassword(changePasswordRequest, jwt);

    }

    @PostMapping("/refresh")
    AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refresh(refreshTokenRequest);
    }

    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return authService.login(loginRequest);
    }

}
