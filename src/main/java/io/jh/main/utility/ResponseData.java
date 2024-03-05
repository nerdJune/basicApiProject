package io.jh.main.utility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
public class ResponseData<T> {

    @Schema(name = "statusCode", example = "SUCCESS")
    private String statusCode;

    private T data;

    @Builder
    public ResponseData(String statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}