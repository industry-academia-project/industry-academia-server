package me.project.school.industry.academia.global.exception;

import me.project.school.industry.academia.global.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<ErrorDto> handleCustomException(GlobalException e) {
        final ErrorDto errorDto = ErrorDto.builder()
                .message(e.getMessage()).build();
        return ResponseEntity.status(e.getHttpStatus())
                .body(errorDto);
    }
}
