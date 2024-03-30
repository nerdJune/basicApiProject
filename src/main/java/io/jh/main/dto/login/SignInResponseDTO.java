package io.jh.main.dto.login;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class SignInResponseDTO {

    @Setter
    private String grantType;

    @Setter
    private String accessToken;

    @Setter
    private String refreshToken;

    @Setter
    private Long accessTokenExpiresIn;


}
