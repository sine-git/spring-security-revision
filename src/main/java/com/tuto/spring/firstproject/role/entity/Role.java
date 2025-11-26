package com.tuto.spring.firstproject.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuto.spring.firstproject.authority.entity.Authority;
import com.tuto.spring.firstproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Table(name="role")
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "`key`")
    String key;

    @Column()
    String name;

    @Column()
    String description;

    @ManyToMany(mappedBy = "roles")
    Set<Authority> authorities;

    @OneToMany(mappedBy = "role")
    @JsonIgnore()
    Set<User> users;
}
