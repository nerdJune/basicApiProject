package io.jh.main.exception;

import io.jh.main.utility.ResponseData;
import io.jh.main.utility.ResponseUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
//RestControllerAdvice = ControllerAdvice + ResponseBody
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseData<Void>> handleException(Exception exception) {
        return ResponseUtility.createFailResponse("FAIL", HttpStatus.OK);
    }
}
