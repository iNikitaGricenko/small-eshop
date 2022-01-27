package com.example.eshop.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;
    @Column(name = "role_name")
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    Set<User> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Role_" + role;
    }
}
