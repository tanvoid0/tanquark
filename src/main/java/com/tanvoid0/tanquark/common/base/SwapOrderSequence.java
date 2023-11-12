package com.tanvoid0.tanquark.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SwapOrderSequence {
    private long id1;
    private long id2;
    private long version1;
    private long version2;
}

