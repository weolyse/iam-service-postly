package com.postly.iam_service.advice;

import com.postly.iam_service.model.constant.ApiConstants;
import com.postly.iam_service.model.exception.DuplicateDataException;
import com.postly.iam_service.model.exception.InvalidDataException;
import com.postly.iam_service.model.exception.InvalidPasswordException;
import com.postly.iam_service.model.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    protected ResponseEntity<String> handleNotFound(NotFoundException ex) {
        logStackTrace(ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseBody
    protected ResponseEntity<String> handleInvalidData(InvalidDataException ex) {
        logStackTrace(ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateDataException.class)
    @ResponseBody
    protected ResponseEntity<String> handleDuplicateData(DuplicateDataException ex) {
        logStackTrace(ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        logStackTrace(ex);

        HashMap<String, String> errors = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.put("Error", error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected String handleInvalidPassword(InvalidPasswordException ex) {
        return ex.getMessage();
    }

    protected void logStackTrace(Exception ex) {
        StringBuilder stackTrace = new StringBuilder();

        stackTrace.append(ApiConstants.ANSI_RED);

        stackTrace.append(ex.getMessage()).append(ApiConstants.BREAK_LINE);

        if (Objects.nonNull(ex.getCause())) {
            stackTrace.append(ex.getCause().getMessage()).append(ApiConstants.BREAK_LINE);
        }

        Arrays.stream(ex.getStackTrace())
                .filter(st -> st.getClassName().startsWith(ApiConstants.TIME_ZONE_PACKAGE_NAME))
                .forEach(st -> stackTrace
                        .append(st.getClassName())
                        .append(".")
                        .append(st.getMethodName())
                        .append(" (")
                        .append(st.getLineNumber())
                        .append(") ")
                );

        log.error(stackTrace.append(ApiConstants.ANSI_WHITE).toString());
    }
}
