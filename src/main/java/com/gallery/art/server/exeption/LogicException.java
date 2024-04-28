package com.gallery.art.server.exeption;


import com.gallery.art.server.exeption.helper.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogicException extends RuntimeException {

    private ErrorCode code;
    private String id;
    private String message;

    public LogicException(ErrorCode code, Long id) {
        super(code.getKey());
        this.code = code;
        this.id = id.toString();
    }


    public LogicException(ErrorCode code, String message) {
        super(code.getKey());
        this.code = code;
        this.message = message;
    }

    public LogicException(ErrorCode code, Long id, Exception e) {
        super(code.getKey(), e);
        this.code = code;
        this.id = id.toString();
    }

    public String toString() {
        return "LogicException{code=" + this.code + ", id=" + id + '}';
    }

}