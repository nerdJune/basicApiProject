package io.jh.main.dto.board.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
public class BoardWriteRequestDTO {

    //카테고리, 제목, 내용, 작성자
    @Schema(
            name = "boardCategory",
            description = "게시글 카테고리",
            example = "1=자유/2=정보 등등...(정의 필요)")
    private Long boardCategory;

    @Schema(
            name = "boardTitle",
            description = "게시글 제목",
            example = "대단히 반갑습니다.")
    private String boardTitle;

    @Schema(
            name = "boardContent",
            description = "게시글 내용",
            example = "상당히 고맙습니다.")
    private String boardContent;

    @Schema(
            name = "writerId",
            description = "작성자 아이디",
            example = "일단 넣어 놓은 건데 바꿔야함.")
    private Long writerId;
}
