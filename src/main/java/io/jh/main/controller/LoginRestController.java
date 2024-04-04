package io.jh.main.controller;

import io.jh.main.dto.login.SignInRequestDTO;
import io.jh.main.dto.login.SignInResponseDTO;
import io.jh.main.service.LoginService;
import io.jh.main.utility.ResponseData;
import io.jh.main.utility.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/main/v1/auth")
public class LoginRestController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResponseData<SignInResponseDTO>> signIn(
            @RequestBody SignInRequestDTO signInRequestDTO) {

        return ResponseUtility.createPostSyncSuccessResponse(
                loginService.userLogin(signInRequestDTO));
    }

}
