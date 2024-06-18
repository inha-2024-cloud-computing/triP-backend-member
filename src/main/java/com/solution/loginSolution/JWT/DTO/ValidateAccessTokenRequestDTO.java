package com.solution.loginSolution.JWT.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateAccessTokenRequestDTO {
    @NotBlank
    private String accessToken;
}
