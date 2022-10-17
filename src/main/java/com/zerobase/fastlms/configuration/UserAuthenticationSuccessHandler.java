package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.repository.LoginHistoryRepository;
import com.zerobase.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        String userid = authentication.getName();
        memberRepository.findById(userid).ifPresent(e -> {
            e.setLastLoginDt(LocalDateTime.now());
            memberRepository.save(e);
        });

        loginHistoryRepository.save(LoginHistory.builder()
                        .userId(userid)
                        .clientIp(request.getLocalAddr())
                        .userAgent(request.getHeader("User-Agent"))
                        .loginDt(LocalDateTime.now())
                        .build());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
