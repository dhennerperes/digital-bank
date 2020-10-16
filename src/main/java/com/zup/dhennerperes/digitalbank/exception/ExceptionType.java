package com.zup.dhennerperes.digitalbank.exception;

public enum ExceptionType {

    ENTITY_NOT_FOUND("not.found"),
    FORBIDDEN("forbidden"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception"),
    NOT_PERMISSION("not.permission"),
    ENTITY_INVALID("invalid");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }

}
