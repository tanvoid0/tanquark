package com.tanvoid0.tanquark.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class ValidationProperty {
    private String property;
    private String value;
}
