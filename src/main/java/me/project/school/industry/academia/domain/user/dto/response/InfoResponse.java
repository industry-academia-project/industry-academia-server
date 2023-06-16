package me.project.school.industry.academia.domain.user.dto.response;

import lombok.*;
import me.project.school.industry.academia.domain.user.entity.User;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor()
public class InfoResponse {

    private String Message;
    private HttpStatus Status;

    private User user;
}
