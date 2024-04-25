package com.mobilebankingapi.feature.auth;

import com.mobilebankingapi.feature.auth.dto.AuthResponse;
import com.mobilebankingapi.feature.auth.dto.LoginRequest;
import com.mobilebankingapi.feature.auth.dto.RefreshTokenRequest;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);

}
