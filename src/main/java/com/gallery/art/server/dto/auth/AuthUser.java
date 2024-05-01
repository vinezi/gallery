package com.gallery.art.server.dto.auth;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser extends BaseDto {
    private Role role;

    public AuthUser(Long id, Role role) {
        this.id = id;
        this.role = role;
    }
}
