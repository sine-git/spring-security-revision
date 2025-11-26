package com.tuto.spring.firstproject.skill.entity;


import com.tuto.spring.firstproject.cv.entity.Cv;
import jakarta.persistence.*;

import java.util.Set;

@Table(name = "skill")
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column()
    String designation;

    @ManyToMany(mappedBy = "skills")
    Set<Cv> cvs;

}
