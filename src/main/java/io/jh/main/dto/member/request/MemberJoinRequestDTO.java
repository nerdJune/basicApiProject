package io.jh.main.dto.member.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
public class MemberJoinRequestDTO {
    @Schema(
            name = "nickName",
            description = "사용할 닉네임",
            example = "닉네임01")
    @Setter
    private String nickName;

    @Schema(
            name = "email",
            description = "사용할 이메일(아이디)",
            example = "nick_name@gmail.com")
    @Setter
    private String email;

    @Schema(
            name = "password",
            description = "사용할 비밀번호",
            example = "password!1")
    @Setter
    private String password;
}
