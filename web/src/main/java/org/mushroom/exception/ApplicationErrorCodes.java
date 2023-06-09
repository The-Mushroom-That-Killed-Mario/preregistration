package org.mushroom.exception;

public enum ApplicationErrorCodes {
    FATAL_ERROR(1),
    SQL_ERROR(10),
    BAD_REQUEST_PARAMETER(20),
    ENTITY_NOT_FOUND(30);


    public int getCodeId() {
        return codeId;
    }

    private final int codeId;

    ApplicationErrorCodes(int codeId) {
        this.codeId = codeId;
    }
}
