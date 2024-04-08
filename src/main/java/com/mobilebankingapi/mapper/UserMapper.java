package com.mobilebankingapi.mapper;

import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.domain.UserAccount;
import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import com.mobilebankingapi.feature.user.dto.UserDetailResponse;
import com.mobilebankingapi.feature.user.dto.UserResponse;
import com.mobilebankingapi.feature.user.dto.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequst (UserCreateRequst userCreateRequst);

   UserDetailResponse toUserDetailsRespone(User user);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }


}
