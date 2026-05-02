package lk.ijse.vehicleservice.exception;

import lk.ijse.vehicleservice.exception.custom.DuplicateException;
import lk.ijse.vehicleservice.exception.custom.NotFoundException;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIResponse<String>> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.NOT_FOUND.value(),e.getMessage(),e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<APIResponse<String>> handleDuplicateException(DuplicateException e) {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.CONFLICT.value(),e.getMessage(),e.getMessage()) ,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse<String>> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse<String>> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage(),e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}