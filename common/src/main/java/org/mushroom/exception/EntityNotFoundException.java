package org.mushroom.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Number id, Class<?> clazz) {

      super("_"+clazz.getSimpleName() + "_ with id _" + id + "_ is NOT FOUND!");
    }
}