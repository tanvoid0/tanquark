package com.tanvoid0.tanquark.config.auth.vo;

import com.tanvoid0.tanquark.models.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticatedUserVO extends UserVO {
    private String token;
    private LocalDateTime expiresAt;
}
