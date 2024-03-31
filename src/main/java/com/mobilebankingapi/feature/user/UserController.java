package com.mobilebankingapi.feature.user;

import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    void creatNew(@Valid @RequestBody UserCreateRequst userCreateRequst){
        userService.createNewUser(userCreateRequst);
    }
}
