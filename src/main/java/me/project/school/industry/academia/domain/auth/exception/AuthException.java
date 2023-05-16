package me.project.school.industry.academia.domain.auth.exception;

import me.project.school.industry.academia.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class AuthException {

    public static class AlreadyUserException extends GlobalException {
        public AlreadyUserException() {
            super(HttpStatus.CONFLICT, "이미존재하는 회원입니다.");
        }
    }

    public static class NotFoundUserException extends GlobalException {
        public NotFoundUserException() {
            super(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
        }
    }

    public static class NotInaccurateInfo extends GlobalException {
        public NotInaccurateInfo() { super(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 다릅니다."); }
    }

}
