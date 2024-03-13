package io.jh.main.service.security;

import io.jh.main.domain.MemberVO;
import io.jh.main.dto.member.response.MemberResponseDTO;
import io.jh.main.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username -> nick_name or email
        MemberVO memberVO = memberService.userLogin(username);

        return User.builder()
                .username(memberVO.getNickName())
                .password(memberVO.getPassword())
                .roles(memberVO.getAuthRole())
                .build();
    }
}
