package com.gallery.art.server.exeption.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {
    JAVA_ERROR("common.java.error", HttpStatus.INTERNAL_SERVER_ERROR),
    COMMON_OBJECT_NOT_EXISTS("common.object.not.exists", HttpStatus.NOT_FOUND);

    @Getter
    private final String key;
    @Getter
    private final HttpStatus status;

    public String toString() {
        return this.key;
    }
}