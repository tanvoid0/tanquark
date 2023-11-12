package com.tanvoid0.tanquark.common.validator;

import com.tanvoid0.tanquark.common.exception.InvalidStaleVersionException;

public class CommonValidator {
    public static void staleVersionValidator(final long version1, final long version2) throws Exception {
        InvalidStaleVersionException.validate(version1, version2);
    }
}
