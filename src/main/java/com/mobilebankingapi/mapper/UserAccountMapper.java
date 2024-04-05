package com.mobilebankingapi.mapper;

import com.mobilebankingapi.domain.User;
import com.mobilebankingapi.feature.account.dto.UserAccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {
    UserAccountResponse toUserAccountResponse (User user);
}
