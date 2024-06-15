package com.gallery.art.server.exeption;

import com.gallery.art.server.exeption.helper.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ObjectNotExistsException extends LogicException {

    private final ObjectType objectType;
    private final String message;
    private final String fieldValue;

    public ObjectNotExistsException(ObjectType type, Long id) {
        super(ErrorCode.COMMON_OBJECT_NOT_EXISTS, id);
        this.objectType = type;
        this.message = "id";
        this.fieldValue = id.toString();
    }
    public ObjectNotExistsException(ObjectType type, String message) {
        super(ErrorCode.COMMON_OBJECT_NOT_EXISTS,message);
        this.objectType = type;
        this.message = message;
        this.fieldValue = null;
    }

    public ObjectNotExistsException(ObjectType type, String message, Object id) {
        super(ErrorCode.COMMON_OBJECT_NOT_EXISTS, Objects.toString(id));
        this.objectType = type;
        this.message = message;
        this.fieldValue = Objects.toString(id);
    }

    public enum ObjectType {
        post,
        postCollection,
        email,
        file,
        user
    }

    public String toString() {
        return String.format("ObjectNotExistsException{type=%s, by %s=%s}", this.objectType, this.message, this.fieldValue);
    }
}
