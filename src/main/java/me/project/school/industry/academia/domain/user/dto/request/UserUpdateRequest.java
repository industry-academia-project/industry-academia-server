package me.project.school.industry.academia.domain.user.dto.request;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserUpdateRequest {

    private String name;
    private String author_name;
    private String introduction;
    private Integer career;
    private String specialized;
}
