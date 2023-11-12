package com.tanvoid0.tanquark.models.portfolio.career.organization;

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
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Organization extends BaseEntityOrdered implements Serializable {
    public static final String BASE_NAME = "Organization";

    @Serial
    private static final long serialVersionUID = -557593147072260070L;
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

}
