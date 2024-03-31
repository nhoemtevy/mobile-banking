package com.mobilebankingapi.feature.user;

import com.mobilebankingapi.domain.Role;
import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import com.mobilebankingapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public void createNewUser(UserCreateRequst userCreateRequst) {

        if (userRepository.existsByPhoneNumber(userCreateRequst.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been exited!"
            );
        }

        if (userRepository.existsByNationalCardId(userCreateRequst.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Nation card ID has already been exited!"
            );
        }

        if (userRepository.existsByStudentIdCard(userCreateRequst.studentId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already been exited!"
            );
        }

        if (!userCreateRequst.password()
                .equals(userCreateRequst.comfirmedPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCreateRequst(userCreateRequst);
        user.setUuid(UUID.randomUUID().toString());

        user.setProfileImage("avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setDeleted(false);

        // Assign default user role
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role USER has not been found!"));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

    }
}
