package com.tanvoid0.tanquark.models.portfolio.skill_group;

import com.tanvoid0.tanquark.common.base.BaseEntityOrdered;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseSkill extends BaseEntityOrdered implements Serializable {

    @Serial
    private static final long serialVersionUID = -5515796402037749601L;

    @Column(nullable = false, unique = true)
    private String name;

    private String category;

    private String icon;
    private String image;

    private String fluency;
    private float fluencyVal;

    private String description;

}
