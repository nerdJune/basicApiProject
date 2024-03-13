package io.jh.main.utility;

import io.jh.main.domain.PagingVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ResponsePagingData<T> extends ResponseData {

    @Schema(name = "statusCode", example = "SUCCESS")
    private String statusCode;

    private List<T> data;
    private PagingVO paging;

    @Builder(builderClassName = "ByPageData", builderMethodName = "byPageData")
    public ResponsePagingData(
            String statusCode, List<T> data, PagingVO paging) {
        this.statusCode = statusCode;
        this.data = data;
        this.paging = paging;
    }

    public static <T> ResponsePagingData<T> of(
            String statusCode, Page<T> page) {
        return ResponsePagingData.<T>byPageData()
                .statusCode(statusCode)
                .data(page.getContent())
                .paging(PagingVO.of(page))
                .build();
    }
}
