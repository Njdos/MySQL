package com.example.sweater.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String firstname;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String lastname;

    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String date;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String password;

    @Transient
    @NotEmpty(message = "Password confirmation cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String password2;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid Email")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String phone;
    private String gender;

    private String filenameq;

    private boolean active;

    private String activatorCode;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)///
    private Set<Message> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }
}