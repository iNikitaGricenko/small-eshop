package com.example.eshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column(name = "login", length = 345, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", length = 150)
    private String firstName;
    @Column(name = "second_name", length = 165)
    private String secondName;
    @Column(name = "surname", length = 150)
    private String surname;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "activated")
    private boolean isActivated = Boolean.FALSE;
    @Column(name = "activation_code")
    private String activationCode;
    @Enumerated(EnumType.STRING)
    private Role role;
}
