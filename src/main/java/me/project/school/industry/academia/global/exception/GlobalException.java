package me.project.school.industry.academia.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
}
