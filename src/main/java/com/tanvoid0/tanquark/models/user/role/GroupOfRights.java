package com.tanvoid0.tanquark.models.user.role;

import com.tanvoid0.tanquark.common.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_of_rights")
public class GroupOfRights extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3716690826903604425L;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "group_of_rights_roles",
            joinColumns = @JoinColumn(name = "group_of_rights_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
