package io.jh.main.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class SignInRequestDTO {
    @Schema(
            name = "email",
            description = "이메일(아이디)",
            example = "nick_name@gmail.com")
    @Setter
    private String email;

    @Schema(
            name = "password",
            description = "비밀번호",
            example = "password!1")
    @Setter
    private String password;
}
