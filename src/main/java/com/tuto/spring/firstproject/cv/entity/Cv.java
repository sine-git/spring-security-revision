package com.tuto.spring.firstproject.cv.entity;


import com.tuto.spring.firstproject.skill.entity.Skill;
import com.tuto.spring.firstproject.user.entity.User;
import jakarta.persistence.*;

import java.util.Set;

@Table(name = "cv")
@Entity
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id ;

    @Column()
    String username;
    @Column()
    String password;

    @Column()
    String salt;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;

    @ManyToMany()
    @JoinTable(
            name = "cv_skill",
            joinColumns = @JoinColumn(
            name = "cv_id"
    ), inverseJoinColumns = @JoinColumn(
            name = "skill_id"
    ))
    Set<Skill> skills;
}
