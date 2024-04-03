package com.mobilebankingapi.mapper;

import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import com.mobilebankingapi.feature.user.dto.UserDetailResponse;
import com.mobilebankingapi.feature.user.dto.UserResponse;
import com.mobilebankingapi.feature.user.dto.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequst (UserCreateRequst userCreateRequst);

   UserDetailResponse toUserDetailsRespone(User user);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);

}
