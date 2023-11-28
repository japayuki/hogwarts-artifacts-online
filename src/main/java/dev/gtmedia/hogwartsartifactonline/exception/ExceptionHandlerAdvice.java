package dev.gtmedia.hogwartsartifactonline.exception;

import dev.gtmedia.hogwartsartifactonline.system.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

//    @ExceptionHandler(value = ArtifactNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    ResultResponse handleArtifactNotFoundException(ArtifactNotFoundException ex){
//        return new ResultResponse(false, 404, ex.getMessage());
//    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResultResponse handleObjectNotFoundException(ObjectNotFoundException ex){
        return new ResultResponse(false, 404, ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResultResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        var errorsMap = new HashMap<String, String>(allErrors.size());
        for (ObjectError error : allErrors) {
            errorsMap.put(((FieldError) error).getField(),error.getDefaultMessage());
        }
        return new ResultResponse(false, 400, "BAD_REQUEST",errorsMap);
    }
}
