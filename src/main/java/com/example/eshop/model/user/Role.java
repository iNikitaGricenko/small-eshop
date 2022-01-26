package com.example.eshop.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {

    @Id
    @Column(nullable = false)
    private Long role_id;
    @Column(name = "role_name")
    private String role;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    List<User> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "Role_" + role;
    }
}
