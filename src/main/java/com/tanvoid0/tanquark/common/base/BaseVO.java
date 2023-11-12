package com.tanvoid0.tanquark.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1110451622927908191L;

    public Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long version;

}
