package com.mobilebankingapi.feature.user;

import com.mobilebankingapi.base.BasedMessage;
import com.mobilebankingapi.domain.Role;
import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import com.mobilebankingapi.feature.user.dto.UserResponse;
import com.mobilebankingapi.feature.user.dto.UserUpdateRequest;
import com.mobilebankingapi.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public void createNewUser(UserCreateRequst userCreateRequst) {

        log.info("user : {}",userCreateRequst.toString());
//
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

        if (userRepository.existsByStudentIdCard(userCreateRequst.studentIdCard())) {
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
//
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
        System.out.println(user.toString());

        userRepository.save(user);

    }

    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest) {

        // check uuid if exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        log.info("before user: {}", user);

        userMapper.fromUserUpdateRequest(userUpdateRequest, user);


        user = userRepository.save(user);


        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse findByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        return userMapper.toUserResponse(user);
    }

    @Override
    public Page<UserResponse> findList(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        // Invoke findAll(pageRequest)
        Page<User> users = userRepository.findAll(pageRequest);
        // Map result of pagination to response
        return users.map(userMapper::toUserResponse);

    }

    @Transactional
    @Override
    public BasedMessage blockByUuid(String uuid) {
        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User has not been found!");
        }

        userRepository.blockByUuid(uuid);

        return new BasedMessage("User has been blocked");
    }

    @Override
    public void deletedByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found!"));
        userRepository.delete(user);
    }

    @Override
    public void enableUserByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setDeleted(false); // Set isDeleted to false to enable user
        userRepository.save(user);
    }

    @Override
    public void disableUserByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setDeleted(true); // Set isDeleted to true to disable user
        userRepository.save(user);
    }

    @Override
    public Page<UserResponse> findAllUser(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page,limit);
        Page<User> users = userRepository.findAll(pageRequest);
        return users.map(userMapper::toUserResponse);
    }


}
