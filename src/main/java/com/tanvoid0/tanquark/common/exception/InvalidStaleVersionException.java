package com.tanvoid0.tanquark.common.exception;

import java.io.Serial;

public class InvalidStaleVersionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -841802431300936591L;

    public InvalidStaleVersionException() {
        super("Requested version of object does not match with existing object in the database");
    }

    public static void validate(final long version1, final long version2) throws Exception {
        if (version1 != version2) {
            throw new InvalidStaleVersionException();
        }
    }
}
