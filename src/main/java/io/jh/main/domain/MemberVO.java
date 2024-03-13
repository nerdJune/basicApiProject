package io.jh.main.domain;

import io.jh.main.dto.member.request.MemberJoinRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
@DynamicUpdate
@DynamicInsert
public class MemberVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth_type")
    private String authType;

    @Column(name = "auth_role")
    private String authRole;    //ROLE_USER,ROLE_ADMIN

    @Column(name = "password_wrong_count")
    private Long passwordWrongCount;    //비밀번호 틀린 횟수

    @Column(name = "member_level")
    private Integer memberLevel;    //레벨

    @Column(name = "member_exp")
    private Integer memberExp;  //경험치

    @Column(name = "content_count")
    private Integer contentCount;   //작성 게시글 수

    @Column(name = "reply_count")
    private Integer replyCount; //작성 댓글 수(댓글+대댓글)

    @Column(name = "last_login_date")
    private OffsetDateTime lastLoginDate;   //최근 로그인 일시

    @Column(name = "join_date")
    private OffsetDateTime joinDate;    //가입일

    @Builder(

            builderClassName = "joinUserDataBuilder", buildMethodName = "joinUserDataBuilder")
    public MemberVO(MemberJoinRequestDTO memberJoinRequestDTO) {
        BeanUtils.copyProperties(memberJoinRequestDTO, this);
        //비밀번호 변경 만들어야함~
        //this.password = memberJoinRequestDTO.getPassword().toLowerCase();
    }
}
