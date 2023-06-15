package me.project.school.industry.academia.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void userInfo() {

        //return User;
    }

}
