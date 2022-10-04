package com.zerobase.fastlms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    // Spring update로 인해 WebSecurityConfigurerAdapter 지원이 사라짐
    // 예전처럼 상속받아 오버라이딩으로 사용하는게 아니라 Bean으로 등록해 사용해야한다
    // 다른 부분은 bean 등록으로 변경을 했는데 SecurityFilterChain 부분이 적용이 안되는거같음
    // 왜 그러는지 찾아봐야한다.
    // 공부가 더 필요함!
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception {

        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/"
                        , "/member/register"
                        , "/member/email-auth"
                        , "/member/find_password"
                        , "/member/reset/password")
                .permitAll();

        http.authorizeRequests()
                        .antMatchers("/admin/**")
                                .hasAuthority("ROLE_ADMIN");

        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .permitAll();

        http.csrf()
                .disable();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/error/denied");

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager manager (
            HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailsService userDetailsService
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and().build();
    }
}
