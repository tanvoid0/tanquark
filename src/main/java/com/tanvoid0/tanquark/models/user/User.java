package com.tanvoid0.tanquark.models.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tanvoid0.tanquark.common.base.BaseEntity;
import com.tanvoid0.tanquark.models.user.role.Role;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@UserDefinition
public class User extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 375190894097691951L;

    public final String _type = "User";

    @Nonnull
    @Username
    @Column(nullable = false, unique = true)
    @Email
    public String email;

    @Column(nullable = false)
    @Nonnull
    @Password
    @ToString.Exclude
    @JsonIgnore
    public String password;

    @Nonnull
    @Column(nullable = false)
    public String username;

    public String name;

    public String phone;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Roles
    private Set<Role> roles = new HashSet<>();

    public Set<String> getRoles() {
        return roles.stream().map(role -> role.getName().toString()).collect(Collectors.toSet());
    }

    public void addRole(Role role) {
        final Set<Role> tempRoles = roles;
        roles = new HashSet<>();
        if (Objects.nonNull(tempRoles)) {
            roles.addAll(tempRoles);
        }
        roles.add(role);
    }

}
