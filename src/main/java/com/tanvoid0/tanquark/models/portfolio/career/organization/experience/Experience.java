package com.tanvoid0.tanquark.models.portfolio.career.organization.experience;

import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.organization.Organization;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "career_experience")
public class Experience extends Organization implements Serializable {
    public static final String NAME = "Experience";

    @Serial
    private static final long serialVersionUID = -1750320303362125624L;

    @Column(nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

}
