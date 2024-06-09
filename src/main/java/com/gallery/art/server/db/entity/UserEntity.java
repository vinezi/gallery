package com.gallery.art.server.db.entity;

import com.gallery.art.server.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "email_confirmed")
    private boolean emailConfirmed;

    @JsonIgnore
    private String password;

    @OneToOne
    @JoinColumn(name="image_id")
    private ImageEntity image;

    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String code;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<PostCollection> userCollection = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<PostEntity> posts = new HashSet<>();


    public UserEntity(String name, String email, boolean emailConfirmed, String password, boolean deleted, Role role, String code) {
        this.name = name;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.password = password;
        this.deleted = deleted;
        this.role = role;
        this.code = code;
    }
}
