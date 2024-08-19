package com.project.surferthon_inha.login.user.dto.response;

import com.project.surferthon_inha.login.user.entity.Role;
import com.project.surferthon_inha.login.user.entity.User;
import com.project.surferthon_inha.login.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformResponseDTO {

    private Long id;

    private UserType userType;

    private Role role;

    private String sub;

    private String name;

    public UserInformResponseDTO(User user) {
        this.id = user.getId();
        this.userType = user.getUserType();
        this.role = user.getRole();
        this.sub = user.getSub();
        this.name = user.getName();
    }
}
