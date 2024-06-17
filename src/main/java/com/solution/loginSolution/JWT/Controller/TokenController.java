package com.solution.loginSolution.JWT.Controller;

import com.solution.loginSolution.JWT.DTO.ValidateAccessTokenRequestDTO;
import com.solution.loginSolution.JWT.DTO.RefreshTokenRequestDTO;
import com.solution.loginSolution.JWT.Exception.RefreshTokenExpiredException;
import com.solution.loginSolution.JWT.Service.AccessTokenService;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.JWT.auth.JwtToken;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final AccessTokenService accessTokenService;

    private final RefreshTokenService refreshTokenService;

    private final JwtTokenProvider jwtTokenProvider;

    @RequestMapping(method = RequestMethod.POST, value = "/validate")
    public ResponseEntity<Boolean> validateAccessToken(@RequestBody @Valid ValidateAccessTokenRequestDTO validateAccessTokenRequestDTO) throws RefreshTokenExpiredException {
        if (accessTokenService.validateAccessToken(validateAccessTokenRequestDTO))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reIssue")
    public ResponseEntity<JwtToken> reIssueAccessToken(@RequestBody @Valid RefreshTokenRequestDTO refreshTokenRequestDTO) throws RefreshTokenExpiredException {
        JwtToken jwtToken = refreshTokenService.reIssueTokens(refreshTokenRequestDTO.toEntity(jwtTokenProvider));
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
