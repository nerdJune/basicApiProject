package io.jh.main.dto.board.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.jh.main.domain.board.BoardVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDTO implements Serializable {

    @Schema(name = "boardId", example = "1")
    private Long boardId;

    @Schema(name = "boardCategory", example = "2")
    //변환 필요함
    private Long boardCategory;

    @Schema(name = "boardCategoryName", example = "자유")
    private String boardCategoryName;

    @Schema(name = "boardTitle", example = "대단히 반갑습니다.")
    private String boardTitle;

    @Schema(name = "boardContent", example = "상당히 고맙습니다.")
    private String boardContent;

    @Schema(name = "viewCount", example = "0")
    private Long viewCount;     //event driven or trigger

    @Schema(name = "replyCount", example = "0")
    private Long replyCount;    //event driven or trigger

    @Schema(name = "writerId", example = "1")
    //이것도 변환 해야함 ㅋㅋ
    private Long writerId; //member_id

    @Schema(name = "writeDatetime", example = "writeDatetime")
    private OffsetDateTime writeDatetime;

    @Schema(name = "updateDatetime", example = "updateDatetime")
    private OffsetDateTime updateDatetime;

    @Schema(name = "nickName", example = "hi")
    private String nickName;

    @Schema(name = "email", example = "zzzskys@naver.com")
    private String email;

    @Builder
    public BoardResponseDTO(BoardVO boardVO, String nickName, String email) {
        BeanUtils.copyProperties(boardVO, this);
        this.nickName = nickName;
        this.email = email;
    }

}
