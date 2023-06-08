package org.mushroom.controller;

import org.mushroom.exception.IllegalRequestException;
import org.springframework.validation.BindingResult;

public abstract class BaseController {
    protected void checkBindingResult(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalRequestException(bindingResult);
        }
    }
}
