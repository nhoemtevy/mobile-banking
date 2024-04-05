package com.mobilebankingapi.exception;

import com.mobilebankingapi.base.BaseError;
import com.mobilebankingapi.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class MediaUploadException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BasedErrorResponse handleValidattionErrors(MethodArgumentNotValidException ex) {
        BaseError baseError = new BaseError();
        List<Map<String, Object>> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("field",fieldError.getField());
                    error.put("message",fieldError.getDefaultMessage());

                    //errorList.add(error);
                });
        baseError.setCode(HttpStatus.BAD_GATEWAY.getReasonPhrase());
        baseError.setDescription(baseError.getDescription());
        return new BasedErrorResponse();
    }

}
