package com.mobilebankingapi.feature.user;

import com.mobilebankingapi.base.BaseMessage;
import com.mobilebankingapi.base.BaseResponse;
import com.mobilebankingapi.feature.user.dto.UserCreateRequst;
import com.mobilebankingapi.feature.user.dto.UserResponse;
import com.mobilebankingapi.feature.user.dto.UserUpdateProfileImageRequest;
import com.mobilebankingapi.feature.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void creatNewUser(@Valid @RequestBody UserCreateRequst userCreateRequst){
        userService.createNewUser(userCreateRequst);
    }


    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }



    @GetMapping("/{uuid}")
    UserResponse findByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    BaseMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }

    @PutMapping("/deleted/{uuid}")
    public void deletedUser(@PathVariable String uuid) {
        userService.deletedByUuid(uuid);
    }

    @PutMapping("/enable/{uuid}")
    public void enableUser(@PathVariable String uuid) {
        userService.enableUserByUuid(uuid);
    }

    @PutMapping("/disable/{uuid}")
    public void disableUser(@PathVariable String uuid) {
        userService.disableUserByUuid(uuid);
    }

    @GetMapping
    Page<UserResponse> findAllUser(
            @RequestParam (required = false, defaultValue = "0") int page,
            @RequestParam (required = false, defaultValue = "2") int limit
            ){
        return userService.findAllUser(page, limit);
    }

    @PutMapping("/{uuid}/profile-image")
    BaseResponse updateProfileImage(@PathVariable String uuid,
                                       @Valid @RequestBody UserUpdateProfileImageRequest userUpdateProfileImageRequest) {
        String newProfileImageUri = userService.updateProfileImage(uuid, userUpdateProfileImageRequest.mediaName());
        return BaseResponse.builder()
                .payload(newProfileImageUri)
                .build();
    }

}
