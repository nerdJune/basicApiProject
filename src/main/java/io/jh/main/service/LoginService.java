package io.jh.main.service;

import io.jh.main.domain.MemberVO;
import io.jh.main.dto.login.SignInRequestDTO;
import io.jh.main.dto.login.SignInResponseDTO;
import io.jh.main.repository.MemberRepository;
import io.jh.main.utility.TokenUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final TokenUtility tokenUtility;
    private final PasswordEncoder passwordEncoder;

    public SignInResponseDTO userLogin(SignInRequestDTO signInRequestDTO) {
        log.info("LoginService In");

        MemberVO memberVO = memberRepository.findByEmail(signInRequestDTO.getEmail()).orElseThrow(
                () -> {
                    log.error("존재하지 않는 회원입니다. 조회시간 : {}");
                    throw new RuntimeException("누구야 이사람");
                });

        checkPassword(signInRequestDTO.getPassword(), memberVO.getPassword());

        return tokenUtility.generateToken(memberVO.getEmail(), memberVO.getAuthRole());
    }

    private void checkPassword(String loginPassword, String pw) {
        if(!passwordEncoder.matches(loginPassword, pw)) {
            throw new BadCredentialsException("wrong password!");
        }
    }
}
