package com.mobilebankingapi.security;


import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.feature.user.RoleRepository;
import com.mobilebankingapi.feature.user.UserRepository;
import com.mobilebankingapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        //load user from db
        User user = userRepository.findByPhoneNumber(phoneNumber);
               //.orElseThrow(() -> new UsernameNotFoundException("User"));

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);

        log.info("User: {}", user);

        return customUserDetails;
    }
}
