package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.tanvoid0.tanquark.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "career_organization")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Organization extends BaseEntity implements Serializable {
    public static final String BASE_NAME = "Organization";

    @Serial
    private static final long serialVersionUID = -557593147072260070L;
    protected Long orderSeq;
    @Column(nullable = false)
    private String _type;
    @Column(nullable = false)
    private String title;
    private String image;
    private String logo;
    private String institution;
    private String address;
    private String timeline;
    @Column(length = 3000)
    private String description;
    @Column(length = 3000)
    private String activities;

    @PostPersist
    protected void postPersist() {
        this.orderSeq = this.id;
    }
}
