package com.example.eshop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
@Table(name = "users")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @EqualsAndHashCode.Include
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id")
            },
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id",
                    referencedColumnName = "role_id")
            })
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(toSet());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActivated();
    }
}
