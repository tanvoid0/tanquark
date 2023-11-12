package com.tanvoid0.tanquark.models.user.role;

import com.tanvoid0.tanquark.common.base.BaseEntity;
import io.quarkus.security.jpa.RolesValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2239256730509848511L;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    @RolesValue
    private ERole name;

    @Column(length = 500)
    private String description;

}

