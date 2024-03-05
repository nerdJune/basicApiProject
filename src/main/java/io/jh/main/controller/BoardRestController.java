package io.jh.main.controller;

import io.jh.main.dto.board.request.BoardWriteRequestDTO;
import io.jh.main.dto.board.response.BoardResponseDTO;
import io.jh.main.enums.BoardSearchTypeEnum;
import io.jh.main.service.BoardService;
import io.jh.main.utility.ResponseData;
import io.jh.main.utility.ResponsePagingData;
import io.jh.main.utility.ResponseUtility;
import io.jh.main.wrapper.CustomPageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/main/v1/board")
public class BoardRestController {
    private final BoardService boardService;

    @Operation(
            summary = "초간단 게시글 작성",
            method = "POST",
            description = "매우 쉽게 글 작성. 바로 써짐")
    @PostMapping
    public ResponseEntity<ResponseData<Void>> writeBoard(@RequestBody BoardWriteRequestDTO boardWriteRequestDTO) {
        boardService.writeBoard(boardWriteRequestDTO);
        return ResponseUtility.createPostSyncSuccessResponse();
    }

    @Operation(
            summary = "초간단 게시글 수정",
            method = "POST",
            description = "매우 쉽게 글 수정. 게시 글이 존재 하는 지만 체크.")
    @PostMapping("/{boardId}")
    public ResponseEntity<ResponseData<Void>> updateBoard(
            @PathVariable(name = "boardId") Long boardId,
            @RequestBody BoardWriteRequestDTO boardWriteRequestDTO) {
        boardService.updateBoard(boardId, boardWriteRequestDTO);
        return ResponseUtility.createPostSyncSuccessResponse();
    }

    @Operation(
            summary = "게시글 단건 조회",
            method = "GET",
            description = "boardId를 가지고 1건만 조회함",
            parameters = {@Parameter(name = "boardId", description = "게시글 아이디")})
    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseData<BoardResponseDTO>> selectBoard(@PathVariable(name = "boardId") Long boardId) {
        System.out.println("selectBoard >>>>>>> " + boardId);
        return ResponseUtility.createGetSuccessResponse(boardService.selectBoard(boardId));
    }

    @Operation(
            summary = "게시글 목록 조회",
            method = "GET",
            description = "검색어를 가지고 조회",
            parameters = {
                    @Parameter(name = "type", description = "검색 구분자(TITLE/CONTENT/WRITER)"),
                    @Parameter(name = "searchText", description = "검색어")
            })
    @GetMapping
    public ResponseEntity<ResponsePagingData<BoardResponseDTO>> selectBoardList(
            @RequestParam(name = "type") BoardSearchTypeEnum type,
            @RequestParam(name = "searchText", required = false) String searchText,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false) String sort) {
        Pageable pageable = CustomPageRequest.of(page, size, sort);
        System.out.println("selectBoardList >>>>>>> " + searchText);
        return ResponseUtility.createSuccessPagingResponse(
                boardService.selectBoardList(searchText, type, pageable));

    }
}
