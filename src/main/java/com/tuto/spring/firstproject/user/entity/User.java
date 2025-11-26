package com.tuto.spring.firstproject.user.entity;


import com.tuto.spring.firstproject.cv.entity.Cv;
import com.tuto.spring.firstproject.role.entity.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column()
    String username;
    @Column()
    String password;
    @Column()
    String salt;
    @OneToMany( mappedBy = "user")
    Set<Cv> cvs;
    @ManyToOne()
    @JoinColumn(name = "role_id")
    Role role;
}
