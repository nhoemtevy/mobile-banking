package com.mobilebankingapi.mapper;

import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import com.mobilebankingapi.feature.user.dto.UserDetailsRespone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequst (UserCreateRequst userCreateRequst);

    void fromUserCreateRequest2(@MappingTarget User user, UserCreateRequst userCreateRequest);
    UserDetailsRespone toUserDetailsResponse(User user);
}
