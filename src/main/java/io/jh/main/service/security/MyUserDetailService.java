package io.jh.main.service.security;

import io.jh.main.domain.MemberVO;
import io.jh.main.dto.member.response.MemberResponseDTO;
import io.jh.main.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("in loadUserByUsername");

        //username -> email
        MemberVO memberVO = memberService.userLogin(username);

        log.info("end loadUserByUsername : " + memberVO.toString());
        return User.builder()
                .username(memberVO.getEmail())
                .password(memberVO.getPassword())
                .roles(memberVO.getAuthRole())
                .build();
    }
}
