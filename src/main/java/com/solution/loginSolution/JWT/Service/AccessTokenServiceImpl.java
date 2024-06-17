package com.solution.loginSolution.JWT.Service;

import com.solution.loginSolution.JWT.DTO.ValidateAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean validateAccessToken(ValidateAccessTokenRequestDTO validateAccessTokenRequestDTO) {
        try {
            jwtTokenProvider.validateAccessToken(validateAccessTokenRequestDTO.getAccessToken());
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
