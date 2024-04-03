package com.mobilebankingapi.feature.user;

import com.mobilebankingapi.base.BasedMessage;
import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import com.mobilebankingapi.feature.user.dto.UserResponse;
import com.mobilebankingapi.feature.user.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void createNewUser (UserCreateRequst userCreateRequst);

    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    UserResponse findByUuid(String uuid);    Page<UserResponse> findList(int page, int limit);

    BasedMessage blockByUuid(String uuid);

    void deletedByUuid(String uuid);

    void enableUserByUuid(String uuid);
    void disableUserByUuid(String uuid);


    Page<UserResponse> findAllUser(int page, int limit);

}
