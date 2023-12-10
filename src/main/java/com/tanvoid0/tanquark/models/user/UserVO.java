package com.tanvoid0.tanquark.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserVO {
    public String phone;
    private Long id;
    private String email;
    private String name;
    private String username;
    private Set<String> roles;
}
