package com.mobilebankingapi.feature.auth;

import com.mobilebankingapi.feature.auth.dto.AuthResponse;
import com.mobilebankingapi.feature.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);
}
