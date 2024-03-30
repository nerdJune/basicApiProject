package io.jh.main.controller;

import io.jh.main.dto.login.SignInRequestDTO;
import io.jh.main.dto.login.SignInResponseDTO;
import io.jh.main.dto.member.response.MemberResponseDTO;
import io.jh.main.dto.member.request.MemberJoinRequestDTO;
import io.jh.main.service.LoginService;
import io.jh.main.service.MemberService;
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
@RequestMapping(path = "/main/v1/user")
public class MemberRestController {

    private final MemberService memberService;

    @Operation(
            summary = "초간단 회원 가입",
            method = "POST",
            description = "매우 쉽게 회원 가입. 이메일 인증 등 넣지 않았음.")
    @PostMapping
    public ResponseEntity<ResponseData<Void>> joinUser(@RequestBody MemberJoinRequestDTO memberJoinRequestDTO) {
        memberService.joinUser(memberJoinRequestDTO);
        return ResponseUtility.createPostSyncSuccessResponse();
    }

    @Operation(
            summary = "회원 단건 조회",
            method = "GET",
            description = "nickName을 가지고 단건 조회, LIKE 검색 아님.",
            parameters = {@Parameter(name = "nickName", description = "조회할 회원의 nickName")})
    @GetMapping("/{nickName}")
    public ResponseEntity<ResponseData<MemberResponseDTO>> selectMember(@PathVariable(name = "nickName") String nickName) {
        System.out.println(">>>>>>> " + nickName);
        return ResponseUtility.createGetSuccessResponse(memberService.selectMember(nickName));
    }
    @Operation(
            summary = "회원 목록 조회",
            method = "GET",
            description = "일단 목록만 조회함. 정렬 없고 + 회원 검색 추가 안함")
    @GetMapping
    public ResponseEntity<ResponsePagingData<MemberResponseDTO>> selectMemberList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false) String sort) {
        Pageable pageable = CustomPageRequest.of(page, size, sort);
        return ResponseUtility.createSuccessPagingResponse(
                memberService.selectMemberList(pageable));
    }

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResponseData<SignInResponseDTO>> signIn(
            @RequestBody SignInRequestDTO signInRequestDTO) {

        return ResponseUtility.createPostSyncSuccessResponse(
                loginService.userLogin(signInRequestDTO));
    }
}
