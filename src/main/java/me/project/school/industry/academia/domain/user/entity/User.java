package me.project.school.industry.academia.domain.user.entity;

import lombok.*;
import me.project.school.industry.academia.global.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Author author;

    @Builder
    public User(String email, String name,  String password, UserRole userRole, Author author) {
        this.email = email;
        this.name = name;
        this.password = PasswordEncoder(password);
        this.userRole = userRole;
        this.author = author;
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
