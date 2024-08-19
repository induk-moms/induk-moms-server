package com.project.surferthon_inha.login.user.dto.request;

import com.project.surferthon_inha.login.user.entity.Role;
import com.project.surferthon_inha.login.user.entity.User;
import com.project.surferthon_inha.login.user.entity.UserType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

    @NotBlank
    private String sub;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    public User toEntity() {
        return User.builder()
                .userType(UserType.DEFAULT)
                .role(Role.USER)
                .sub(sub)
                .password(password)
                .name(name)
                .build();
    }
}
