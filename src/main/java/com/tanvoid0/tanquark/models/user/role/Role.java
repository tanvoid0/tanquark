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

import java.io.Serializable;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements Serializable {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @RolesValue
    private ERole name;

    @Column(length = 500)
    private String description;

}

