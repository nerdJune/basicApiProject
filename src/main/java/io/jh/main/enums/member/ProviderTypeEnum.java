package io.jh.main.enums.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProviderTypeEnum {

    GOOGLE("GOOGLE", "구글로그인"),
    ID("ID", "이메일");

    private final String code;
    private final String displayName;

    public static ProviderTypeEnum of(String code) {
        return Arrays.stream(ProviderTypeEnum.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(ID);
    }
}
