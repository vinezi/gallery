package com.gallery.art.server.dto.auth;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthUser extends BaseDto {
    private Role role;
}
