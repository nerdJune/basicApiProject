package io.jh.main.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jh.main.dto.board.response.BoardResponseDTO;
import io.jh.main.enums.BoardSearchTypeEnum;
import io.jh.main.model.board.BoardVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.jh.main.model.board.QBoardVO.boardVO;
import static io.jh.main.model.QMemberVO.memberVO;

@Repository
@Slf4j
public class BoardQueryRepository extends QuerydslRepositorySupport {
    public BoardQueryRepository() {
        super(BoardVO.class);
    }

    private JPAQueryFactory queryFactory;

    @PersistenceContext(unitName = "mainEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Page<BoardResponseDTO> selectBoardList(String searchText, BoardSearchTypeEnum type, Pageable pageable) {
        JPQLQuery<BoardResponseDTO> query =
                queryFactory.select(
                                Projections.bean(
                                    BoardResponseDTO.class,
                                        boardVO.boardId,
                                        boardVO.boardCategory,
                                        boardVO.boardContent,
                                        boardVO.boardTitle,
                                        boardVO.replyCount,
                                        boardVO.writeDatetime,
                                        boardVO.writerId,
                                        boardVO.updateDatetime,
                                        boardVO.viewCount,
                                        memberVO.nickName
                                    )
                        )
                        .from(boardVO)
                        .join(memberVO)
                        .on(boardVO.writerId.eq(memberVO.memberId))
                        .where(
                                search(searchText, type),
                                boardVO.deleteYn.eq("N")
                        )
                        .orderBy(boardVO.writeDatetime.desc());

        query = query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        List<BoardResponseDTO> content = query.fetch();

        return PageableExecutionUtils.getPage(content, pageable, query::fetchCount);

    }

    public BooleanExpression search(String searchText, BoardSearchTypeEnum type) {
        if(BoardSearchTypeEnum.TITLE.equals(type)) {
            return boardVO.boardTitle.contains(searchText);
        } else if(BoardSearchTypeEnum.CONTENT.equals(type)) {
            return boardVO.boardContent.contains(searchText);
        } else if(BoardSearchTypeEnum.WRITER.equals(type)) {
            return boardVO.writerId.eq(Long.parseLong(searchText));
        }
        return null;
    }
}
