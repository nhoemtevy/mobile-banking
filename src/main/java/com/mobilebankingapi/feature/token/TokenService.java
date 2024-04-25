package com.mobilebankingapi.feature.token;

import com.mobilebankingapi.feature.auth.dto.AuthResponse;
import org.springframework.security.core.Authentication;


public interface TokenService {

    AuthResponse createToken(Authentication auth);

    String createAccessToken(Authentication auth);

    String createRefreshToken(Authentication auth);

}
