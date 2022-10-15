package com.bobocode.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "userName")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String email;

    @NaturalId
    @Column(nullable = false, unique = true)
    protected String userName;

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;
}
