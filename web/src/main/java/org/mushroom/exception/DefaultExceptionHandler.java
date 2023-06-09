package org.mushroom.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.mushroom.exception.ApplicationErrorCodes.BAD_REQUEST_PARAMETER;
import static org.mushroom.exception.ApplicationErrorCodes.ENTITY_NOT_FOUND;



@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        /* Handles all other exceptions. Status code 500. */


        return new ResponseEntity<>(
                new ErrorMessage(
                        System.currentTimeMillis(),
                        ENTITY_NOT_FOUND.getCodeId(),
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException e) {
//        /* Handles all other exceptions. Status code 500. */
//
//        String exceptionUniqueId = generator.uuidGenerator();
//
//        log.error(exceptionUniqueId + e.getMessage(), e);
//
//        return new ResponseEntity<>(
//                new ErrorMessage(
//                        exceptionUniqueId,
//                        USER_NOT_FOUND.getCodeId(),
//                        e.getMessage()
//                ),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({EntityNotFoundException.class, DeletedEntityException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(RuntimeException e) {

//        String exceptionUniqueId = generator.uuidGenerator();

//        log.error(exceptionUniqueId + e.getMessage(), e);

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

//        log.error(exceptionUniqueId + e.getMessage(), e);

        return new ResponseEntity<>(
                new ErrorMessage(
                        System.currentTimeMillis(),
                        BAD_REQUEST_PARAMETER.getCodeId(),
                        e.getBindingResult().getAllErrors().toString()
                ),
                HttpStatus.BAD_REQUEST);
    }
}
