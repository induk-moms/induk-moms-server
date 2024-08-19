package com.project.surferthon_inha.login.jwt.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReIssueTokenRequestDTO {

    @NotBlank
    private String refreshToken;
}
