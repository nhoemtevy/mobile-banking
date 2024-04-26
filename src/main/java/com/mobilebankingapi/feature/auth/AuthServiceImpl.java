package com.mobilebankingapi.feature.auth;


import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.feature.auth.dto.AuthResponse;
import com.mobilebankingapi.feature.auth.dto.ChangePasswordRequest;
import com.mobilebankingapi.feature.auth.dto.LoginRequest;
import com.mobilebankingapi.feature.auth.dto.RefreshTokenRequest;
import com.mobilebankingapi.feature.token.TokenService;
import com.mobilebankingapi.feature.user.UserRepository;
import com.mobilebankingapi.feature.user.UserService;
import com.mobilebankingapi.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtEncoder refreshJwtEncoder;

    @Qualifier("refreshJwtEncoder")
    @Autowired
    public void setRefreshJwtEncoder(JwtEncoder refreshJwtEncoder) {
        this.refreshJwtEncoder = refreshJwtEncoder;
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, Jwt jwt) {
        User user = userRepository.findByPhoneNumber(jwt.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (!changePasswordRequest.newPassword().equals(changePasswordRequest.confirmedPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Password does not match");
        }

        if (!passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Old password does not match");
        }

        if (passwordEncoder.matches(changePasswordRequest.newPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "New password is not allowed");
        }


        user.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
        userRepository.save(user);

    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.phoneNumber(),
                loginRequest.password()
        );
        auth = daoAuthenticationProvider.authenticate(auth);

        return tokenService.createToken(auth);

    }

    @Override
    public AuthResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        Authentication auth = new BearerTokenAuthenticationToken(
                refreshTokenRequest.refreshToken()
        );

        auth = jwtAuthenticationProvider.authenticate(auth);

        return tokenService.createToken(auth);

    }
}
