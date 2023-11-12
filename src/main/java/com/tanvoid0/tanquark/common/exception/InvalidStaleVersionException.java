package com.tanvoid0.tanquark.common.exception;

public class InvalidStaleVersionException extends RuntimeException {
    public InvalidStaleVersionException() {
        super("Requested version of object does not match with existing object in the database");
    }

    public static void validate(final long version1, final long version2) throws Exception {
        if (version1 != version2) {
            throw new InvalidStaleVersionException();
        }
    }
}
