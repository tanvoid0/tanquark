package com.tanvoid0.tanquark.models.portfolio.career.organization.certificate;

import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.organization.Organization;
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
@Table(name = "organization_certificate")
public class Certificate extends Organization implements Serializable {
    public static final String NAME = "Certificate";

    @Serial
    private static final long serialVersionUID = 6262234836769761123L;

    private String graduation;

    private String url;

    private String certificate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

}
