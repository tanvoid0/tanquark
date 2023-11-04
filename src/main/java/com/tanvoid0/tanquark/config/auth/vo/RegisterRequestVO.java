package com.tanvoid0.tanquark.config.auth.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "RegisterRequest")
public class RegisterRequestVO {
    @Email
    @NotNull
    @Schema(example = "user@mail.com")
    private String email;

    @NotNull
    @Schema(example = "user123")
    private String password;

    @NotNull
    @Schema(example = "Guest User")
    private String name;

}
