package org.mushroom.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.mushroom.exception.ApplicationErrorCodes.BAD_REQUEST_PARAMETER;
import static org.mushroom.exception.ApplicationErrorCodes.ENTITY_NOT_FOUND;
import static org.mushroom.exception.ApplicationErrorCodes.FATAL_ERROR;
import static org.mushroom.exception.ApplicationErrorCodes.SQL_ERROR;


@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        /* Handles all other exceptions. Status code 500. */


        return new ResponseEntity<>(
                new ErrorMessage(
                        System.currentTimeMillis(),
                        FATAL_ERROR.getCodeId(),
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({EntityNotFoundException.class, DeletedEntityException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(RuntimeException e) {

        return new ResponseEntity<>(
                new ErrorMessage(
                        System.currentTimeMillis(),
                        ENTITY_NOT_FOUND.getCodeId(),
                        e.getMessage()
                ),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<ErrorMessage> handleIllegalRequestException(IllegalRequestException e) {

        return new ResponseEntity<>(
                new ErrorMessage(
                        System.currentTimeMillis(),
                        BAD_REQUEST_PARAMETER.getCodeId(),
                        e.getBindingResult().getAllErrors().toString()
                ),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorMessage> handleSQLException(DataAccessException e) {

        return new ResponseEntity<>(
                new ErrorMessage(
                        System.currentTimeMillis(),
                        SQL_ERROR.getCodeId(),
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST);
    }
}
