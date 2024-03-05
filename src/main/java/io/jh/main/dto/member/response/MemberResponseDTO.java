package io.jh.main.dto.member.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.jh.main.model.MemberVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponseDTO {

    @Schema(name = "writerId", example = "1")
    private Long writerId;

    @Schema(name = "nickName", example = "brick_sand")
    private String nickName;

    @Schema(name = "email", example = "email@email.com")
    private String email;

    @Schema(name = "auth_type", example = "ID/GOOGLE/...")
    private String authType;

    @Schema(name = "auth_role", example = "MEMBER/ADMIN")
    private String authRole;

    @Schema(name = "member_level", example = "1")
    private Integer memberLevel;    //레벨

    @Schema(name = "member_exp", example = "1")
    private Integer memberExp;  //경험치

    @Schema(name = "content_count", example = "1")
    private Integer contentCount;   //작성 게시글 수

    @Schema(name = "reply_count", example = "1")
    private Integer replyCount; //작성 댓글 수(댓글+대댓글)

    @Schema(name = "last_login_date")
    private OffsetDateTime lastLoginDate;   //최근 로그인 일시

    @Schema(name = "join_date")
    private OffsetDateTime joinDate;    //가입일

    @Builder
    public MemberResponseDTO(MemberVO memberVO) {
        BeanUtils.copyProperties(memberVO, this);
//        this.nickName = memberVO.getNickName();
//        this.email = memberVO.getEmail();
    }
}
