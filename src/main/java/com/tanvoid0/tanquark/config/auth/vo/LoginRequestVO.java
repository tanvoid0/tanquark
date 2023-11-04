package com.tanvoid0.tanquark.config.auth.vo;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "LoginRequest")
public class LoginRequestVO {
    @Nonnull
    @Email
    @Schema(example = "user@mail.com")
    private String email;

    @Schema(example = "user123")
    @Nonnull
    private String password;
}
