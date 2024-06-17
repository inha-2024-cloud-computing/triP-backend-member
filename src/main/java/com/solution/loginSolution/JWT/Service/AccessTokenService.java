package com.solution.loginSolution.JWT.Service;

import com.solution.loginSolution.JWT.DTO.ValidateAccessTokenRequestDTO;

public interface AccessTokenService {

    boolean validateAccessToken(ValidateAccessTokenRequestDTO validateAccessTokenRequestDTO);
}
