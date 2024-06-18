package com.solution.loginSolution.User.OAuth2.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import com.solution.loginSolution.User.OAuth2.GeneralOAuth2User.GeneralOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class oAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Value("${client.url}")
    private String clientUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Authentication Success");
        GeneralOAuth2User oAuth2User = (GeneralOAuth2User) authentication.getPrincipal();
        Long userId = oAuth2User.getId();
        String userEmail = oAuth2User.getUserEmail();
        String userName = oAuth2User.getUserName();

        Map<String, Object> responseBodyWriting = jwtTokenProvider.generateResponseBody(userId, userName, userEmail);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(responseBodyWriting));

        String parameter = jwtTokenProvider.generateResponseParameter(userId, userName, userEmail);
        String clientFull = clientUrl + "/token-exchange?" + parameter;
        getRedirectStrategy().sendRedirect(request, response, clientFull);


        // REST 형식이므로 필요없음
        //super.onAuthenticationSuccess(request, response, authentication);
    }
}
