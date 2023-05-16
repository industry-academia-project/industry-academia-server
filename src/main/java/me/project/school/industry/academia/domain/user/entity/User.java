package me.project.school.industry.academia.domain.user.entity;

import lombok.*;
import me.project.school.industry.academia.global.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Builder
    public User(String email, String name,  String password, UserRole userRole) {
        this.email = email;
        this.name = name;
        this.password = PasswordEncoder(password);
        this.userRole = userRole;

    }

    /**
     * Please explain the class!!
     *
     * @filename       : User.java
     * @author        : tomato
     * @since         : 2023/04/12
     * @param password salt 지정하여 인코딩 횟수 감추기          :
     */
    private String PasswordEncoder(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);

        return bCryptPasswordEncoder.encode(password);
    }

}
