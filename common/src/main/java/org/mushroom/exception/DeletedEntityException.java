package org.mushroom.exception;

public class DeletedEntityException extends RuntimeException {
    public DeletedEntityException(Number id, Class<?> clazz) {

        super("_" + clazz.getSimpleName() + "_ with id _" + id + "_ is DELETED");
    }
}