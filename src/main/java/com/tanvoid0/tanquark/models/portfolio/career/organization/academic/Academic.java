package com.tanvoid0.tanquark.models.portfolio.career.organization.academic;

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
@Table(name = "organization_academic")
public class Academic extends Organization implements Serializable {

    public static final String NAME = "Academic";

    @Serial
    private static final long serialVersionUID = 3662133158529326555L;

    @Column(nullable = false)
    private String graduation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

}
