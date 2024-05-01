package com.gallery.art.server.dto.user;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.dto.Image;
import com.gallery.art.server.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseDto { //todo v
    private String name;
    private String email;
    private Image image;
    private Role role;
}
