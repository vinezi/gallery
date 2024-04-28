package com.gallery.art.server.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

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

    @OneToOne(cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name="image_id")
    private ImageEntity image;

//    private boolean deleted = false;
}
