package io.jh.main.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class PagingVO {
    @Schema(name = "size", example = "10")
    private int size;

    @Schema(name = "page", example = "1")
    private int page;

    @Schema(name = "totalPages", example = "1")
    private int totalPages;

    @Schema(name = "totalElements", example = "1")
    private long totalElements;

    @Builder
    public PagingVO(int size, int page, int totalPages, long totalElements) {
        this.size = size;
        this.page = page;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public static PagingVO of(Page<?> page) {
        return PagingVO.builder()
                .size(page.getSize())
                .page(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
