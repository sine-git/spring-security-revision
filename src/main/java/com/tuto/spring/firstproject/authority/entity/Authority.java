package com.tuto.spring.firstproject.authority.entity;


import com.tuto.spring.firstproject.role.entity.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Table(name="authority")
@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "`key`")
    String key;

    @Column()
    String name;

    @Column()
    String description;

    @ManyToMany()
    @JoinTable(
            name = "role_authority",
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name= "role_id")
    )
    Set<Role> roles;
}
