package org.mushroom.exception;

public class IsDeletedEntityException extends RuntimeException {
    public IsDeletedEntityException(Number id, Class<?> clazz) {

        super("_"+clazz.getSimpleName() + "_ with id _" + id + "_ is DELETED");
    }
}