package io.jh.main.service;

import io.jh.main.domain.board.BoardCategoryVO;
import io.jh.main.dto.board.request.BoardWriteRequestDTO;
import io.jh.main.dto.board.response.BoardResponseDTO;
import io.jh.main.enums.BoardSearchTypeEnum;
import io.jh.main.domain.MemberVO;
import io.jh.main.domain.board.BoardVO;
import io.jh.main.repository.BoardCategoryRepository;
import io.jh.main.repository.BoardQueryRepository;
import io.jh.main.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service("boardService")
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;

    private final BoardRepository boardRepository;

    private final BoardQueryRepository boardQueryRepository;

    private final BoardCategoryRepository boardCategoryRepository;

    private final RedisService redisService;

    public void writeBoard(BoardWriteRequestDTO boardWriteRequestDTO) {

        //data validation 추가 필요
        /*
            제목, 내용이 적절 한 지 필터링 필요. ex) 금칙어
         */
        //회원 인지
        MemberVO memberVO = memberService.userCheck(boardWriteRequestDTO.getEmail());
        boardWriteRequestDTO.setWriterId(memberVO.getMemberId());

        //dto to vo
        BoardVO boardVO =
                BoardVO.boardWriteBuilder().boardWriteRequestDTO(boardWriteRequestDTO).boardWriteBuilder();

        boardRepository.save(boardVO);
    }

    @Transactional
    public void updateBoard(Long boardId, BoardWriteRequestDTO boardWriteRequestDTO) {

        //게시글 존재 여부 확인
        BoardVO boardVO = boardRepository.findById(boardId).orElseThrow(() -> {
            logger.error("존재 하지 않는 게시물 입니다.");
            throw new RuntimeException("이 글 뭐야");
        });

        //data validation 추가 필요
        boardVO.setBoardCategory(boardWriteRequestDTO.getBoardCategory());
        boardVO.setBoardTitle(boardWriteRequestDTO.getBoardTitle());
        boardVO.setBoardContent(boardWriteRequestDTO.getBoardContent());
        boardVO.setUpdateDatetime(OffsetDateTime.now());

        //jpa dirty check
        //boardRepository.save(boardVO);
    }

    /*
        게시글 조회
            단건 조회
                - 게시글 id > eq
            
     */
    public BoardResponseDTO selectBoard(Long boardId) {
        BoardVO boardVO = boardRepository.findById(boardId).orElseThrow(() -> {
            logger.error("존재 하지 않는 게시물 입니다.");
            throw new RuntimeException("이 글 뭐야");
        });

        MemberVO memberVO = memberService.selectMemberInfo(boardVO.getWriterId());

        List<BoardCategoryVO> boardCategoryVOList = getBoardCategory();


        boardCategoryVOList.stream().filter(f -> f.getCategoryId().equals(boardVO.getBoardCategory())).findFirst().get().getNameKr();

        //BoardResponse
        return BoardResponseDTO.builder()
                .boardVO(boardVO)
                .nickName(memberVO.getNickName())
                .email(memberVO.getEmail())
                .build();
    }

    /*
        다건 조회(목록)
            - 게시글 제목 > like
            - 게시글 내용 > like
            
            - 게시글 작성자 > eq
     */
    public Page<BoardResponseDTO> selectBoardList(String searchText, BoardSearchTypeEnum type, Pageable pageable) {
        //검색어, 구분자(제목/내용/작성자)
        try {
            if(BoardSearchTypeEnum.WRITER.equals(type)) {
                searchText = memberService.selectMemberIdFromNickName(searchText).toString();
            }
            if(searchText == null) searchText = "";
            searchText = searchText.trim().replace(" ","");

            Page<BoardResponseDTO> result = boardQueryRepository.selectBoardList(searchText, type, pageable);

            List<BoardCategoryVO> boardCategoryVOList = redisRedis();

            result.forEach(r -> {
                r.setBoardCategoryName(
                        boardCategoryVOList.stream().filter(i -> r.getBoardCategory().equals(i.getCategoryId())).findFirst().get().getNameKr()
                );
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BoardCategoryVO> getBoardCategory() {
        //System.out.println("list > " + list);
        return boardCategoryRepository.findAll();
    }

    public void setBoardCategoryListForRedis() {
        redisService.addRedisListData("category", getBoardCategory());
    }

    public List<BoardCategoryVO> redisRedis() {
        if(redisService.asdf("category") == null) {
            setBoardCategoryListForRedis();
        }
        return (List<BoardCategoryVO>)redisService.asdf("category");
    }
//    public void setBoardCategoryListForRedis() {
//        redisService.addRedisListData("category", getBoardCategory());
//    }
}
