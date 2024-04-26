package com.mobilebankingapi.feature.auth;

import com.mobilebankingapi.feature.auth.dto.AuthResponse;
import com.mobilebankingapi.feature.auth.dto.ChangePasswordRequest;
import com.mobilebankingapi.feature.auth.dto.LoginRequest;
import com.mobilebankingapi.feature.auth.dto.RefreshTokenRequest;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthService {

    void changePassword(ChangePasswordRequest changePasswordRequest, Jwt jwt);

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);

}
