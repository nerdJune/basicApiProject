package io.jh.main.service;

import io.jh.main.dto.member.response.MemberResponseDTO;
import io.jh.main.dto.member.request.MemberJoinRequestDTO;
import io.jh.main.domain.MemberVO;
import io.jh.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    //회원 가입
    public void joinUser(MemberJoinRequestDTO memberJoinRequestDTO) {

        //회원 정보 체크 메서드 따로 분류 해서 작업.
        checkJoinUserData(memberJoinRequestDTO);

        //저장 전 DTO -> VO
        MemberVO memberVO =
                MemberVO.builder().memberJoinRequestDTO(memberJoinRequestDTO).joinUserDataBuilder();
        memberVO.setPassword(passwordEncoder.encode(memberJoinRequestDTO.getPassword()));

        memberRepository.save(memberVO);
    }

    //회원 단건 조회
    public MemberResponseDTO selectMemberFromNickname(String nickName) {
        //VO를 ResponseDTO로 만들어보자
        MemberVO memberVO = memberRepository.findByNickName(nickName).orElseThrow(
                () -> {
                    logger.error("존재하지 않는 회원입니다. 조회시간 : {}");
                    throw new RuntimeException("누구야 이사람");
                }
        );
        System.out.println("단건 조회 > " + memberVO.toString());
        return MemberResponseDTO.builder().memberVO(memberVO).build();
    }

    public MemberVO selectMemberInfo(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> {
            logger.error("존재하지 않는 회원입니다. 조회시간 : {}");
            throw new RuntimeException("누구야 이사람");
        });
    }
    
    public Long selectMemberIdFromNickName(String nickName) {
        Optional<MemberVO> memberVO = memberRepository.findByNickName(nickName);
        return memberVO.map(MemberVO::getMemberId).orElse(0L);
    }

    public String selectMemberIdToString(String nickName) {
        Optional<MemberVO> memberVO = memberRepository.findByNickName(nickName);
        return memberVO.map(vo -> vo.getMemberId().toString()).orElse("");
    }

    public Page<MemberResponseDTO> selectMemberList(Pageable pageable) {
        List<MemberVO> memberList =
               memberRepository.findAll(pageable).stream().toList();

        return new PageImpl<>(
                memberList.stream()
                        .map(MemberResponseDTO::new)
                        .skip(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .collect(Collectors.toList()),
                pageable,
                memberList.size()
        );
    }

    public MemberVO userCheck(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                    () -> {
            logger.error("존재하지 않는 회원입니다. 조회시간 : {}");
            throw new RuntimeException("누구야 이사람");
        });
    }

    public void userExistCheck(String email) {
        memberRepository.findByEmail(email).orElseThrow(
                () -> {
                    logger.error("존재하지 않는 회원입니다. 조회시간 : {}");
                    throw new RuntimeException("누구야 이사람");
                });
    }

    //가입 시 닉네임 존재 여부
    public boolean checkExistNickname(String nickName) {
        if(nickName == null) {
            throw new RuntimeException("닉네임이 비어 있습니다.");
        } else {
            return memberRepository.existsByNickName(nickName);
        }
    }

    private void checkJoinUserData(MemberJoinRequestDTO memberJoinRequestDTO) {
        if(checkExistNickname(memberJoinRequestDTO.getNickName())) {
            throw new RuntimeException("이미 존재 하는 닉네임 입니다.");
        }

        //이메일 체크

    }
}
