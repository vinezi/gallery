package com.gallery.art.server.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "tag")
@NoArgsConstructor
public class TagEntity extends BaseEntity {

    @Column
    private String name;
}
