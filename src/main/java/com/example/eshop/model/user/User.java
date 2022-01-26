package com.example.eshop.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @Column(nullable = false)
    private Long user_id;
    private String login;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id")
            },
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id",
                    referencedColumnName = "role_id")
            })
    @JsonManagedReference
    private List<Role> roles = new ArrayList<>();

}
