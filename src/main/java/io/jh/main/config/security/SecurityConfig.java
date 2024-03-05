package io.jh.main.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

//    private final CorsProperties corsProperties;
//
//    @Bean
//    public SecurityFilterChain filterChain(
//            HttpSecurity http
//            //, CustomAuthenticationFilter customAuthenticationFilter,
//            //JwtAuthorizationFilter jwtAuthorizationFilter
//    ) throws Exception {
//        log.debug("[+] WebSecurityConfig Start !!! ");
//        return http
//                //.csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/resources/**").permitAll()
//                        .requestMatchers("/main/v1").permitAll()
//                        .requestMatchers("/main/v1/user").permitAll()
//                        .anyRequest().authenticated()
//                )
//                //.addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .successHandler(new SimpleUrlAuthenticationSuccessHandler("/main/v1/main"))
//                        .permitAll()
//                )
//                //.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        //비밀번호 암호화
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        //정적 자원 접근 허용
//        return web -> web.ignoring()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
//        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
//        corsConfig.setAllowedOriginPatterns(
//                Arrays.asList(corsProperties.getAllowedOriginPatterns().split(",")));
//        corsConfig.setAllowCredentials(true);
//        corsConfig.setMaxAge(corsProperties.getMaxAge());
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return source;
//    }
}
