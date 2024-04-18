package io.jh.main.domain.board;

import io.jh.main.dto.board.request.BoardWriteRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;

@Entity(name = "board")
@Table(name = "board")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
@DynamicUpdate
@DynamicInsert
public class BoardVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_category")
    private Long boardCategory;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "view_count")
    private Long viewCount;     //event driven or trigger

    @Column(name = "reply_count")
    private Long replyCount;    //event driven or trigger

    @Column(name = "writer_id")
    private Long writerId; //member_id

    @Column(name = "write_datetime")
    private OffsetDateTime writeDatetime;

    @Column(name = "update_datetime")
    private OffsetDateTime updateDatetime;

    @Column(name = "delete_yn")
    private String deleteYn;

    @Builder(builderMethodName = "boardWriteBuilder", buildMethodName = "boardWriteBuilder")
    public BoardVO(BoardWriteRequestDTO boardWriteRequestDTO) {
        BeanUtils.copyProperties(boardWriteRequestDTO, this);
    }

    @Builder(builderMethodName = "boardUpdateBuilder", buildMethodName = "boardUpdateBuilder")
    public BoardVO(String a) {
        //BeanUtils.copyProperties(boardWriteRequestDTO, this);
    }

    @PreUpdate
    public void update() {
        this.updateDatetime = OffsetDateTime.now();
    }
}
