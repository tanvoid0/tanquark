package com.tanvoid0.tanquark.models.user.role;


import java.util.EnumSet;
import java.util.Set;

public enum ERole {
    USER,
    ADMIN;

    public static Set<ERole> getAllAsSet() {
        return EnumSet.allOf(ERole.class);
    }
}
