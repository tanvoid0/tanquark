package com.tanvoid0.tanquark.common.base.file;

public enum EFileType {
    IMAGE,
    VIDEO,
    OTHER,
    DOCUMENT;

    public static EFileType parse(final String value) {
        if (value == null) {
            return null;
        }
        if (value.startsWith("image")) {
            return IMAGE;
        } else if (value.startsWith("video")) {
            return VIDEO;
        } else if (value.startsWith("application/pdf") || value.startsWith("text")) {
            return DOCUMENT;
        } else {
            return OTHER;
        }
    }

}
