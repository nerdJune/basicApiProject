package io.jh.main.config.security;

import io.jh.main.service.security.MyUserDetailService;
import io.jh.main.utility.TokenUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    //private final MyUserDetailService myUserDetailService;
    private final TokenUtility tokenUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("in JwtAuthFilter");
        String authHeader = subStringToken(request.getHeader("Authorization"));
        String userName = null;

        if(authHeader != null && tokenUtility.validateToken(authHeader)) {
            userName = tokenUtility.getSubject(authHeader);
            Authentication auth = tokenUtility.getAuthentication(authHeader);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

//        if(userName != null
//                && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);
//            if(tokenUtility.validateToken(authHeader)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
        log.info("end JwtAuthFilter");
        filterChain.doFilter(request, response);
    }

    private String subStringToken(String header) {
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
